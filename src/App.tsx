import {type ReactNode, useEffect, useState} from "react";
import {getTodos} from "./api/todo.ts";
import {TodoContext} from "./context/TodoContext.tsx";
import type {TODO} from "./types/todo.type.ts";
import type {AxiosError, AxiosResponse} from "axios";
import { Route, Routes } from "react-router-dom";

import Home from "./pages/home";
import Add from "./pages/add";

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
                <h1>TODO APP</h1>
                <p>by Ilham Muhammad</p>

                <main className={"main"}>
                    <Routes>
                        <Route path={"/"} element={<Home />}/>
                        <Route path={"/add"} element={<Add />}/>
                    </Routes>
                </main>
            </div>
        </TodoContext>
    )
}

export default App
