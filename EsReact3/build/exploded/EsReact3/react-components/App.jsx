'use strict';

class Task extends React.Component {
    constructor(props) {
        super(props);
        this.taskOnclick = this.taskOnclick.bind(this);
    }

    taskOnclick(e){
        this.props.taskOnclick(document.getElementsByName("writterTask")[0].value)
    }

    render() {
        return (
            <div name="task">
                <input type="text" name="writterTask" placeholder="Inserisci qui il task"/><button onClick={this.taskOnclick}>Aggiungi elemento</button>
            </div>
        );
    }
}

class TaskList extends React.Component {
    constructor(props) {
        super(props);
        this.removalFunction = this.removalFunction.bind(this);
    }

    removalFunction(e){
        this.props.removalFunction(e.target.innerHTML);
    }

    render() {
        const listItems = this.props.listaDiTask.map((d) => <li name={d} onClick={this.removalFunction}>{d}</li>);
        return (

            <div name="taskList">
                <h1>Ecco la lista di tasks</h1>
                <ul name="taskUnorderedList">
                {listItems}
                </ul>
            </div>

        );
    }
}


class App extends React.Component {
    constructor(props) {
        super(props);
        this.taskOnclick = this.taskOnclick.bind(this);
        this.removalFunction = this.removalFunction.bind(this);
        this.state = {text:[]};
    }

    taskOnclick(toAdd){
        let newState = this.state.text.concat(toAdd);
        this.setState({text: newState});
    }

    removalFunction(toRemove){
        let newState = this.state.text.filter(item => item!==toRemove);
        this.setState({text: newState});
    }

    render() {
        return (
            <div name="appBody">
                <h1>Task list app</h1>
                <Task taskOnclick={this.taskOnclick}/>
                <TaskList listaDiTask={this.state.text} removalFunction={this.removalFunction}/>
            </div>
        );
    }
}

ReactDOM.render(<App/>, document.getElementById("root"));