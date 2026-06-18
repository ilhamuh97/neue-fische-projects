import {type NavigateFunction, useNavigate} from "react-router-dom";
import {type ReactNode} from "react";
import type {AxiosError} from "axios";

import type {STATUS, TODO} from "../../types/todo.type.ts";

import {deleteTodoById, updateTodoById} from "../../api/todo.ts";

import {useForm} from "react-hook-form";
import {AiOutlineArrowLeft} from "react-icons/ai";

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

    const onSubmit = (formData: FormValues) => {
        updateTodoById(todo.id, {...formData,})
            .catch((error: AxiosError) => console.error(error))
            .finally(() => nav("/"))
    }

    function handleDelete(): void {
        deleteTodoById(todo.id)
            .catch((err) => console.error("Failed to delete todo:", err))
            .finally(() => nav("/"));
    }
    return (
        <div>
            <button className="btn btn-primary" onClick={() => nav("/")}>
                <AiOutlineArrowLeft/> Home
            </button>
            <h2 className="title">TODO Detail</h2>

            <form className={"form"} onSubmit={handleSubmit(onSubmit)}>
                <label>
                    Description:
                    <input
                        className={"input"}
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
                    <button className="btn btn-primary" type="submit" disabled={!isValid}>
                        Update
                    </button>

                    <button className="btn btn-secondary" type="button" onClick={() => reset()}>
                        Reset
                    </button>

                    <button className="btn btn-danger" type="button" onClick={() => handleDelete()}>
                        Delete
                    </button>
                </div>
            </form>
        </div>
    );
}

export default DetailTodo;