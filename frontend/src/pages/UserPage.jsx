import { useEffect, useState } from "react"
import { useParams, Link } from "react-router-dom"
import { fetchUserById } from "@/entities/user/api/usersApi"
import UserInfo from "@/widgets/user-info/UserInfo"
export default function UserPage() {
    const { id } = useParams()

    const [user, setUser] = useState(null)
    const [isLoading, setIsLoading] = useState(true)

    useEffect(() => {
        const load = async () => {
            setIsLoading(true)

            try {
                const data = await fetchUserById(id)

                if (!data) {
                    setUser(null)
                } else {
                    setUser(data)
                }
            } catch (e) {
                setUser(null)
            } finally {
                setIsLoading(false)
            }
        }

        load()
    }, [id])

    if (isLoading) {
        return <div className="p-10">Загрузка...</div>
    }

    if (!user) {
        return <div className="p-10 text-red-500">Пользователь не найден</div>
    }

    return (
        <div className="container mx-auto py-10 space-y-6">

            <Link to="/" className="text-sm underline text-blue-600 hover:text-blue-800">
                Вернуться к списку пользователей
            </Link>

            <UserInfo user={user} />
        </div>
    )
}