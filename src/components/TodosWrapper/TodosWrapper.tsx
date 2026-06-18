import {type ReactNode} from 'react';
import { FaPlus } from "react-icons/fa";
import { type NavigateFunction, useNavigate} from "react-router-dom";

import TodoSection from "../TodoSection/TodoSection.tsx";

import {useTodos} from "../../hooks/useTodos.tsx";

import "./style.css";

function TodosWrapper(): ReactNode {
    const nav: NavigateFunction = useNavigate();
    const {openTodos, inProgressTodos, doneTodos} = useTodos();

    function handleClick() {
        nav("/add")
    }
    return (
        <>
            <button className={"button-primary sticky-right-bottom"} onClick={handleClick}>
                <FaPlus/>
            </button>
            <div className={"sections-wrapper"}>
                <TodoSection title={"Open"} todos={openTodos}/>
                <TodoSection title={"In Progress"} todos={inProgressTodos}/>
                <TodoSection title={"Done"} todos={doneTodos}/>
            </div>
        </>

    );
}

export default TodosWrapper;