import { IoMdArrowDropright, IoMdArrowDropleft } from "react-icons/io";
import { MdDelete } from "react-icons/md";
import {Link} from "react-router-dom"
import type {ReactNode} from "react";

import type {TODO} from "../../types/todo.type.ts";

import "./style.css"

type Props = {
    todo: TODO
    handleDelete: (id: string) => void
    handleNext?: (id: string) => void,
    handleBack?: (id: string) => void,
}

function Todo({todo, handleNext, handleBack, handleDelete}: Props): ReactNode {
    return (
        <div className={"todo"}>
            <Link className={"description"} to={`/${todo.id}`}>{todo.description}</Link>
            <div className={"actions"}>
                {handleBack && (
                    <button
                        className="btn btn-secondary"
                        onClick={() => handleBack(todo.id)}
                    >
                        <IoMdArrowDropleft/>
                    </button>
                )}
                <button className={"btn btn-danger"} onClick={() => handleDelete(todo.id)}>
                    <MdDelete/>
                </button>
                {handleNext && (
                    <button
                        className="btn btn-secondary"
                        onClick={() => handleNext(todo.id)}
                    >
                        <IoMdArrowDropright />
                    </button>
                )}
            </div>

        </div>
    );
}

export default Todo;