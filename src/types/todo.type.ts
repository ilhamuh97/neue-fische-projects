export type TODO = {
    id: string,
    status: STATUS,
    description: string
}

export type STATUS = "OPEN" | "IN_PROGRESS" | "DONE";
