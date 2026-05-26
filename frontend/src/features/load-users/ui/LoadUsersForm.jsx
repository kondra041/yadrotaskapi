import { useState } from "react"

import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { loadUsers } from "@/entities/user/api/usersApi"
import { toast } from "sonner"

export default function LoadUsersForm({ onLoaded }) {

    const [count, setCount] = useState('')
    const [loading, setLoading] = useState(false)

    const handleSubmit = async (e) => {
        e.preventDefault()
        try {
            setLoading(true)
            const result = await loadUsers(count)
            toast.success(result)
            onLoaded?.()

        } finally {
            setLoading(false)
        }
    }

    return (
        <form onSubmit={handleSubmit} className="flex gap-4" >
            <Input placeholder="Количество записей для импорта" type="number" min="2" value={count} onChange={(e) => setCount(e.target.value) } />
            <Button disabled={loading}>
                {loading ? "Загрузка..." : "Загрузить"}
            </Button>
        </form>
    )
}