import TodosWrapper from "../../components/TodosWrapper/TodosWrapper.tsx";
import type {ReactNode} from "react";

function Index(): ReactNode {
    return (
        <div className={"page"}>
            <TodosWrapper />
        </div>
    );
}

export default Index;