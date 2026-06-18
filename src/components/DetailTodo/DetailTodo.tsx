import { useParams} from "react-router-dom";
import {type ReactNode, useEffect, useState} from "react";
import type {AxiosResponse} from "axios";

import type {TODO} from "../../types/todo.type.ts";

import {getTodoById} from "../../api/todo.ts";

import "./style.css"

function DetailTodo(): ReactNode {
    const { id } = useParams();
    const [todo, setTodo] = useState<TODO>({} as TODO)

    useEffect(() => {
        console.log(id)
        if(id) {
            getTodoById(id).then((result: AxiosResponse<TODO>) => setTodo(result.data))
        }
    }, [id])

    return (
        <div>{todo.description}</div>
    );
}

export default DetailTodo;