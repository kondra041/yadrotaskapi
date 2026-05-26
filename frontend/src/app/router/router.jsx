import {
    createBrowserRouter,
} from "react-router-dom"

import HomePage from "@/pages/HomePage"
import UserPage from "@/pages/UserPage"
import RandomUserPage from "@/pages/RandomPage"

export const router =
    createBrowserRouter([
        {
            path: "/",
            element: <HomePage />,
        },
        {
            path: "/:id",
            element: <UserPage />,
        },
        {
            path: "/random",
            element: <RandomUserPage />,
        },
    ])