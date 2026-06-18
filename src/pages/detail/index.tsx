import {type ReactNode, useEffect, useState} from "react";

import DetailTodo from "../../components/DetailTodo/DetailTodo.tsx";
import {getTodoById} from "../../api/todo.ts";
import type {AxiosResponse} from "axios";
import type {TODO} from "../../types/todo.type.ts";
import {useParams} from "react-router-dom";

function Index(): ReactNode {
    const [todo, setTodo] = useState< TODO | undefined | null>(undefined)
    const { id } = useParams();

    useEffect(() => {
        if (!id) return;

        getTodoById(id)
            .then((result: AxiosResponse<TODO>) => {
                setTodo(result.data);
            })
            .catch((error) => {
                setTodo(null);
                console.error(error);
            });
    }, [id]);


    if (todo === undefined) {
        return <div className="page">Loading...</div>;
    }

    if (todo === null) {
        return <div className="page">Todo not found</div>;
    }

    return (
        <div className="page">
            <DetailTodo todo={todo} />
        </div>
    );
}

export default Index;