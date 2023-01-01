'use strict';

class Lavagna extends React.Component {
    constructor() {
        super();
        this.onClick = this.onClick.bind(this);
        //const socket = new WebSocket("ws://localhost:8080/EsReact2/lavagna");
    }

    
    render() {
        return (
            <div class="formBody">
                <textarea name="myTextArea" placeholder="Dati complessivi" row="5" cols="32">
                    {this.props.myText}
                </textarea>
                <button onclick={this.props.resetText}></button>
            </div>
        );
    }
}
