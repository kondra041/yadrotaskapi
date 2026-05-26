export const getGender = (gender) => {
    switch (gender) {
        case "woman":
            return "Женский"

        case "man":
            return "Мужской"

        default:
            return "Не указан"
    }
}