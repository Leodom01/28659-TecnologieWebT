'use strict';

class Task extends React.Component {
    constructor(props) {
        super(props);
    }

    taskOnclick(e){
        this.props.taskOnclick(document.getElementsByName("writterTask")[0].value)
    }

    render() {
        return (
            <div name="task">
                <input type="text" name="writterTask" placeholder="Inserisci qui il task"/><button onCLick={this.taskOnclick}>Aggiungi elemento</button>
            </div>
        );
    }
}
