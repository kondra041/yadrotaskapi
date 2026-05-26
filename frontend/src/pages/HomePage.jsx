import { useEffect, useState } from "react"

import LoadUsersForm from "@/features/load-users/ui/LoadUsersForm"
import UserTable from "@/widgets/user-table/ui/UserTable"
import { fetchUsers } from "@/entities/user/api/usersApi"

import {
    Select,
    SelectContent,
    SelectItem,
    SelectTrigger,
    SelectValue,
} from "@/components/ui/select"

export default function HomePage() {
    const [users, setUsers] = useState([])

    const [page, setPage] = useState(0)
    const [pageInput, setPageInput] = useState(1)
    const [size, setSize] = useState(10)

    const [totalPages, setTotalPages] = useState(0)
    const [totalUsers, setTotalUsers] = useState(0)

    const loadData = async (pageNumber = page, pageSize = size) => {
        const data = await fetchUsers(pageNumber, pageSize)

        setUsers(data.content)
        setPage(data.number)
        setTotalPages(data.totalPages)
        setTotalUsers(data.totalElements)
        setPageInput(data.number + 1)
    }

    useEffect(() => {
        loadData(0, size)
    }, [])

    const goToPage = (newPage) => {
        if (newPage < 0 || newPage >= totalPages) return
        loadData(newPage, size)
    }

    const changeSize = (newSize) => {
        const parsedSize = Number(newSize)
        setSize(parsedSize)
        loadData(0, parsedSize) // при каждой смене размера сброс на 1-ую стр.
    }

    return (
        <div className="container mx-auto py-10 space-y-6">
            <h1 className="text-3xl font-bold">Люди</h1>

            <div className="text-sm text-gray-500">
                Всего пользователей: {totalUsers}
            </div>

            <LoadUsersForm onLoaded={() => loadData(0, size)} />

            <div className="flex items-center justify-between">
                <div className="flex items-center gap-3">
                    <span>Показывать:</span>

                    <Select value={String(size)} onValueChange={changeSize}>
                        <SelectTrigger className="w-[120px]">
                            <SelectValue />
                        </SelectTrigger>

                        <SelectContent>
                            <SelectItem value="10">10</SelectItem>
                            <SelectItem value="20">20</SelectItem>
                            <SelectItem value="30">30</SelectItem>
                            <SelectItem value="40">40</SelectItem>
                            <SelectItem value="50">50</SelectItem>
                        </SelectContent>
                    </Select>
                </div>
            </div>

            <UserTable user={users} />

            {/* ПАГИНАЦИЯ  */}
            <div className="flex gap-3 items-center justify-center pt-4">

                <button
                    className="px-3 py-1 border rounded"
                    onClick={() => goToPage(page - 1)}
                    disabled={page === 0}
                >
                    Назад
                </button>

                {/* INPUT перехода */}
                <div className="flex items-center gap-2">
                    <span>Страница</span>

                    <input
                        className="w-16 border rounded px-2 py-1 text-center"
                        value={pageInput}
                        onChange={(e) => setPageInput(e.target.value)}
                        onKeyDown={(e) => {
                            if (e.key === "Enter") {
                                const targetPage = Number(pageInput) - 1

                                if (
                                    !isNaN(targetPage) &&
                                    targetPage >= 0 &&
                                    targetPage < totalPages
                                ) {
                                    goToPage(targetPage)
                                }
                            }
                        }}
                    />

                    <span>из {totalPages}</span>
                </div>

                <button
                    className="px-3 py-1 border rounded"
                    onClick={() => goToPage(page + 1)}
                    disabled={page + 1 >= totalPages}
                >
                    Вперед
                </button>

            </div>
        </div>
    )
}