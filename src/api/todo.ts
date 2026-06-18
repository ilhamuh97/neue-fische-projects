import { api } from "./axios";
import type {AxiosResponse} from "axios";
import type {TODO} from "../types/todo.type.ts";

export const createTodo: (data: Partial<TODO>) => Promise<AxiosResponse> = (
    data: Partial<TODO>
): Promise<AxiosResponse<TODO>> => {
    return api.put("/todo", data);
};

export const getTodos: () => Promise<AxiosResponse> = (): Promise<AxiosResponse> => api.get("/todo");
export const getTodoById: (id: string) => Promise<AxiosResponse> = (id: string): Promise<AxiosResponse> => api.get(`/todo/${id}`);

export const updateTodoById: (id: string, data: Partial<TODO>) => Promise<AxiosResponse> = (
    id: string,
    data: Partial<TODO>
): Promise<AxiosResponse<TODO>> => {
    return api.put(`/todo/${id}`, data);
};

export const deleteTodoById: (id: string) => Promise<AxiosResponse>  = (id: string): Promise<AxiosResponse> => api.delete(`/todo/${id}`);