import { type ReactNode } from 'react';
import type { TODO } from "../../types/todo.type.ts";
import "./style.css";
import Todo from "../Todo/Todo.tsx";
import { deleteTodoById, updateTodoById } from "../../api/todo.ts";
import { useTodos } from "../../hooks/useTodos.tsx";
import type { AxiosResponse } from "axios";

type Props = {
    title?: string;
    todos?: TODO[];
}

function TodoSection({ title = "Todos", todos = [] }: Props): ReactNode {
    const { setTodos } = useTodos();

    function handleDelete(id: string): void {
        deleteTodoById(id)
            .then(() => {
                setTodos((prev: TODO[]) => prev.filter((todo: TODO) => todo.id !== id));
            })
            .catch((err) => console.error("Failed to delete todo:", err));
    }

    function handleUpdate(id: string, data: Partial<TODO>): void {
        updateTodoById(id, data)
            .then((response: AxiosResponse<TODO>) => {
                const updatedTodo = response.data;
                setTodos((prev: TODO[]) =>
                    prev.map((todo: TODO) => todo.id === id ? updatedTodo : todo)
                );
            })
            .catch((err) => console.error("Failed to update todo:", err));
    }

    const getStatusActions = (todo: TODO) => {
        switch (todo.status) {
            case "OPEN":
                return {
                    handleNext: () => handleUpdate(todo.id, { ...todo, status: "IN_PROGRESS" })
                };
            case "IN_PROGRESS":
                return {
                    handleNext: () => handleUpdate(todo.id, { ...todo, status: "DONE" }),
                    handleBack: () => handleUpdate(todo.id, { ...todo, status: "OPEN" })
                };
            case "DONE":
                return {
                    handleBack: () => handleUpdate(todo.id, { ...todo, status: "IN_PROGRESS" })
                };
            default:
                return {};
        }
    };

    return (
        <div className="todos bg-accent">
            <h2 className="title">{title}</h2>
            <div className="todo-list">
                {todos.map((todo: TODO) => {
                    const actions = getStatusActions(todo);

                    return (
                        <Todo
                            key={todo.id}
                            todo={todo}
                            handleDelete={handleDelete}
                            {...actions}
                        />
                    );
                })}
            </div>
        </div>
    );
}

export default TodoSection;