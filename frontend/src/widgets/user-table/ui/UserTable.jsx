import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { getGender } from "@/lib/converter"
import { Link } from "react-router-dom"

export default function UserTable({ user }) {
    return (
        <Table>
            <TableHeader>
                <TableRow>
                    <TableHead>
                        Пол
                    </TableHead>

                    <TableHead>
                        Имя
                    </TableHead>

                    <TableHead>
                        Фамилия
                    </TableHead>

                    <TableHead>
                        Телефон
                    </TableHead>

                    <TableHead>
                        Email
                    </TableHead>

                    <TableHead>
                        Адрес
                    </TableHead>

                    <TableHead>
                    </TableHead>
                </TableRow>

            </TableHeader>

            <TableBody>

                {user.map((user) => (

                    <TableRow key={user.id}>

                        <TableCell>
                           { getGender(user.gender) }
                        </TableCell>

                        <TableCell>
                            {user.firstName}
                        </TableCell>

                        <TableCell>
                            {user.lastName}
                        </TableCell>

                        <TableCell>
                            {user.phone}
                        </TableCell>

                        <TableCell>
                            {user.email}
                        </TableCell>

                        <TableCell>
                            {user.address}
                        </TableCell>

                        <TableCell>

                            <Link className="underline" to={`/${user.id}`} >
                                Открыть
                            </Link>

                        </TableCell>

                    </TableRow>

                ))}

            </TableBody>

        </Table>
    )
}