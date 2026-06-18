import {type NavigateFunction, useNavigate} from "react-router-dom";
import type {ReactNode} from "react";
import {useForm} from "react-hook-form";
import {type AxiosError, type AxiosResponse} from "axios";

import type {STATUS, TODO} from "../../types/todo.type.ts";

import {useTodos} from "../../hooks/useTodos.tsx";
import {createTodo} from "../../api/todo.ts";

import "./style.css"
import {AiOutlineArrowLeft} from "react-icons/ai";

type FormValues = {
    description: string,
    status: STATUS,
}

function AddTodo(): ReactNode {
    const nav: NavigateFunction = useNavigate();
    const {register, handleSubmit, formState} = useForm<FormValues>({mode: "onChange"});
    const {errors, isValid} = formState;

    const {setTodos} = useTodos();

    const onSubmit = (formData: FormValues) => {
        createTodo({
            ...formData,
            status: "OPEN"
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

            <h2 className="title">Add New TODO</h2>

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

                <div className="buttons">
                    <button className="button-primary" type="submit" disabled={!isValid}>
                        Add
                    </button>

                    <button className="button-secondary" type="reset">
                        Reset
                    </button>
                </div>
            </form>
        </div>
    );
}

export default AddTodo;