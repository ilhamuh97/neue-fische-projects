import {type Context, createContext, type Dispatch, type SetStateAction} from "react";
import type {TODO} from "../types/todo.type.ts";

export type TodoContextType = {
    todos: TODO[],
    setTodos: Dispatch<SetStateAction<TODO[]>>;
}

export const TodoContext: Context<TodoContextType | undefined> = createContext<TodoContextType | undefined>(undefined);