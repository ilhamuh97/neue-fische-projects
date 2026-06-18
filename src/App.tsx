import {type ReactNode, useEffect, useState} from "react";
import {getTodos} from "./api/todo.ts";
import {TodoContext} from "./context/TodoContext.tsx";
import type {TODO} from "./types/todo.type.ts";
import type {AxiosError, AxiosResponse} from "axios";
import OpenTodo from "./components/OpenTodo/OpenTodo.tsx";

import './App.css'

function App(): ReactNode {
    const [todos, setTodos] = useState<TODO[]>([] as TODO[]);

    useEffect((): void => {
        const fetchData: () => void = () => { getTodos()
                .then((response: AxiosResponse<TODO[]>) => setTodos(response.data))
                .catch((e: AxiosError) => console.error(e))
        }

        void fetchData()
    }, [])

    return (
        <TodoContext value={{todos, setTodos}}>
            <div className={"app"}>
                Hello
                <main className={"main"}>
                    <div className={"sections-wrapper"}>
                        <OpenTodo />
                    </div>
                </main>
            </div>
        </TodoContext>
    )
}

export default App
