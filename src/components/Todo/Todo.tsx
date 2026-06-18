import type {TODO} from "../../types/todo.type.ts";
import { IoMdArrowDropright, IoMdArrowDropleft } from "react-icons/io";
import { MdDelete } from "react-icons/md";

import "./style.css"
import type {ReactNode} from "react";

type Props = {
    todo: TODO
    handleDelete: (id: string) => void
    handleNext?: (id: string) => void,
    handleBack?: (id: string) => void,
}

function Todo({todo, handleNext, handleBack, handleDelete}: Props): ReactNode {
    return (
        <div className={"todo"}>
            <div className={"description"}>
                <p>{todo.description}</p>
            </div>
            <div className={"actions"}>
                {handleBack && (
                    <button
                        className="button-secondary"
                        onClick={() => handleBack(todo.id)}
                    >
                        <IoMdArrowDropleft/>
                    </button>
                )}
                <button className={"button-danger"} onClick={() => handleDelete(todo.id)}>
                    <MdDelete/>
                </button>
                {handleNext && (
                    <button
                        className="button-secondary"
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