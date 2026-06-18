import {type ReactNode, useState} from "react";
import { Route, Routes } from "react-router-dom";

import type {TODO} from "./types/todo.type.ts";

import {TodoContext} from "./context/TodoContext.tsx";

import Home from "./pages/home";
import Add from "./pages/add";
import Detail from "./pages/detail";

import './App.css'

function App(): ReactNode {
    const [todos, setTodos] = useState<TODO[]>([] as TODO[]);

    return (
        <TodoContext value={{todos, setTodos}}>
            <div className={"app"}>
                <h1>TODO APP</h1>
                <p>by Ilham Muhammad</p>

                <main className={"main"}>
                    <Routes>
                        <Route path={"/"} element={<Home />}/>
                        <Route path={"/add"} element={<Add />}/>
                        <Route path={"/:id"} element={<Detail />}/>
                    </Routes>
                </main>
            </div>
        </TodoContext>
    )
}

export default App
