import {type ReactNode, useEffect} from "react";
import type {AxiosError, AxiosResponse} from "axios";

import type {TODO} from "../../types/todo.type.ts";

import {getTodos} from "../../api/todo.ts";
import {useTodos} from "../../hooks/useTodos.tsx";

import TodosWrapper from "../../components/TodosWrapper/TodosWrapper.tsx";

function Index(): ReactNode {
    const {setTodos} = useTodos();
    
    useEffect((): void => {
        const fetchData: () => void = () => { getTodos()
            .then((response: AxiosResponse<TODO[]>) => setTodos(response.data))
            .catch((e: AxiosError) => console.error(e))
        }

        void fetchData()
    }, [setTodos])

    return (
        <div className={"page"}>
            <TodosWrapper />
        </div>
    );
}

export default Index;