'use strict';

class TaskList extends React.Component {
    constructor(props) {
        super(props);
        for(var item of this.props.listaTask.split(",")){
            ReactDOM.render(<li>{item}</li>, document.getElementsByName("taskUnorderedList")[0]);
        }
    }

    render() {
        return (

            <div name="taskList">
                <h1>Ecco la lista di tasks</h1>
                <ul name="taskUnorderedList">
                </ul>
            </div>

        );
    }
}
