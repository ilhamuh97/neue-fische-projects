import {type NavigateFunction, useNavigate} from "react-router-dom";
import {type ReactNode} from "react";
import type {AxiosError, AxiosResponse} from "axios";

import type {STATUS, TODO} from "../../types/todo.type.ts";

import {updateTodoById} from "../../api/todo.ts";

import {useForm} from "react-hook-form";
import {AiOutlineArrowLeft} from "react-icons/ai";
import {useTodos} from "../../hooks/useTodos.tsx";

import "./style.css"

type FormValues = {
    description: string,
    status: STATUS,
}

type Props = {
    todo: TODO
}

function DetailTodo({todo}: Props): ReactNode {
    const nav: NavigateFunction = useNavigate();
    const {register, handleSubmit, formState, reset} = useForm<FormValues>({mode: "onChange",  defaultValues: {
            description: todo.description,
            status: todo.status,
        },});
    const {errors, isValid} = formState;
    const {setTodos} = useTodos();

    const onSubmit = (formData: FormValues) => {
        updateTodoById(todo.id, {
            ...formData,
        })
            .then((response: AxiosResponse<TODO>) => setTodos((prevCharacters: TODO[]): TODO[] => [...prevCharacters, response.data]))
            .catch((error: AxiosError) => console.error(error))
            .finally(() => nav("/"))
    }

    return (
        <div>
            <button className="button-primary" onClick={() => nav("/")}>
                <AiOutlineArrowLeft/> Home
            </button>
            <h2 className="title">TODO Detail</h2>

            <form className={"form"} onSubmit={handleSubmit(onSubmit)}>
                <label>
                    Description:
                    <input
                        {...register("description", {
                            required: "Description is required",
                            minLength: {
                                value: 5,
                                message: "Description must be at least 5 characters"
                            }
                        })}
                    />
                    {errors.description && (
                        <p className="error">{errors.description.message}</p>
                    )}
                </label>

                <label>
                    Status
                    <select
                        {...register("status", {required: "Status is required"})}
                    >
                        <option key={"open"} value={"OPEN"}>OPEN</option>
                        <option key={"in_progress"} value={"IN_PROGRESS"}>IN PROGRESS</option>
                        <option key={"done"} value={"DONE"}>DONE</option>
                    </select>
                </label>

                <div className="buttons">
                    <button className="button-primary" type="submit" disabled={!isValid}>
                        Update
                    </button>

                    <button className="button-secondary" type="button" onClick={() => reset()}>
                        Reset
                    </button>
                </div>
            </form>
        </div>
    );
}

export default DetailTodo;