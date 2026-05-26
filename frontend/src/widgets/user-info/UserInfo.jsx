import { getGender } from "@/lib/converter";

export default function UserInfo({ user }) {
    return (
        <div className="max-w-xl mx-auto bg-white shadow-md rounded-2xl p-6 border">

            <h1 className="text-3xl font-bold mb-6">
                {user.firstName} {user.lastName}
            </h1>

            <div className="space-y-3 text-gray-700">

                <div className="flex justify-between border-b pb-2">
                    <span className="font-medium">Пол</span>
                    <span>{getGender(user.gender)}</span>
                </div>

                <div className="flex justify-between border-b pb-2">
                    <span className="font-medium">Имя</span>
                    <span>{user.firstName}</span>
                </div>

                <div className="flex justify-between border-b pb-2">
                    <span className="font-medium">Фамилия</span>
                    <span>{user.lastName}</span>
                </div>

                <div className="flex justify-between border-b pb-2">
                    <span className="font-medium">Телефон</span>
                    <span>{user.phone}</span>
                </div>

                <div className="flex justify-between border-b pb-2">
                    <span className="font-medium">Email</span>
                    <span>{user.email}</span>
                </div>

                <div className="flex justify-between">
                    <span className="font-medium">Место проживания</span>
                    <span className="text-right">
                        {user.address}
                    </span>
                </div>

            </div>
        </div>
    )
}