import type {ReactNode} from "react";

import DetailTodo from "../../components/DetailTodo/DetailTodo.tsx";

function Index(): ReactNode {
    return (
        <div className={"page"}>
            <DetailTodo />
        </div>
    );
}

export default Index;