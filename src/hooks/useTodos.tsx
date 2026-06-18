import { useContext } from "react";
import {TodoContext, type TodoContextType} from "../context/TodoContext.tsx";

export function useTodos(): TodoContextType {
    const context: TodoContextType | undefined = useContext(TodoContext);

    if (!context) {
        throw new Error(
            "context is not available"
        );
    }

    return context;
}