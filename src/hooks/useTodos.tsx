import {useContext} from "react";

import type {TODO} from "../types/todo.type.ts";

import {TodoContext, type TodoContextType} from "../context/TodoContext.tsx";

type GroupedTodo =  {
    openTodos: TODO[],
    inProgressTodos: TODO[],
    doneTodos: TODO[]
}

export function useTodos(): TodoContextType {
    const context: TodoContextType | undefined = useContext(TodoContext);

    if (!context) {
        throw new Error(
            "context is not available"
        );
    }

    const { openTodos, inProgressTodos, doneTodos} = context.todos.reduce(
        (acc: GroupedTodo, todo: TODO): GroupedTodo => {
            if (todo.status === "OPEN") acc.openTodos.push(todo);
            else if (todo.status === "IN_PROGRESS") acc.inProgressTodos.push(todo);
            else if (todo.status === "DONE") acc.doneTodos.push(todo);

            return acc;
        },
        {
            openTodos: [] as TODO[],
            inProgressTodos: [] as TODO[],
            doneTodos: [] as TODO[],
        }
    );

    return {
        ...context,
        openTodos,
        inProgressTodos,
        doneTodos
    };
}