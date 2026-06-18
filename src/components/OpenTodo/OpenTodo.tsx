import {type ReactNode} from 'react';
import {useTodos} from "../../hooks/useTodos.tsx";

function OpenTodo(): ReactNode {
    const {todos} = useTodos();

    console.log("todos", todos)

    return (
        <div></div>
    );
}

export default OpenTodo;