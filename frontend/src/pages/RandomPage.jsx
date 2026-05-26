import { useEffect, useState } from "react"
import { fetchRandomUser } from "@/entities/user/api/usersApi"
import UserInfo from "@/widgets/user-info/UserInfo"
export default function RandomUserPage() {
    const [user, setUser] = useState(null)
    const [isLoading, setIsLoading] = useState(true)

    const loadRandom = async () => {
        setIsLoading(true)

        try {
            const data = await fetchRandomUser()
            setUser(data)
        } catch (e) {
            setUser(null)
        } finally {
            setIsLoading(false)
        }
    }

    useEffect(() => {
        loadRandom()
    }, [])

    if (isLoading) {
        return <div className="p-10">Загрузка...</div>
    }

    if (!user) {
        return <div className="p-10 text-red-500">Не удалось загрузить пользователя</div>
    }

    return (
        <div className="container mx-auto py-10">
           <UserInfo user={user} />
        </div>
    )
}