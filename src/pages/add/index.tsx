import {type ReactNode} from 'react';

import AddTodo from "../../components/AddTodo/AddTodo.tsx";

function Index(): ReactNode {
    return (
        <div className={"page"}>
            <AddTodo/>
        </div>
    );
}

export default Index;