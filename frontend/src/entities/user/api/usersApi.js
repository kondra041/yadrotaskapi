import apiClient from "@/shared/api/apiClient"

export const fetchUsers = async (page = 0, size = 10) => {
    const response = await apiClient.get(`/users?page=${page}&size=${size}`)
    return response.data
}

export const loadUsers = async (count) => {
    const response = await apiClient.post(`/users/load`, { count })
    return response.data
}

export const fetchUserById = async (id) => {
    const response = await apiClient.get(`/users/${id}`)
    return response.data
}

export const fetchRandomUser = async () => {
    const response = await apiClient.get(`/users/random`)
    return response.data
}