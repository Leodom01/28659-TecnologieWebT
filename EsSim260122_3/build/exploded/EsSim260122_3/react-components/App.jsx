'use strict';

class App extends React.Component {
    socket;
    constructor(props) {
        super(props);
        this.state = {
            table: [" ", " ", " ", " ", " ", " ", " ", " ", " ",],
            turn: "no",
            title: "Attendi l'altro giocatore"
        };
        this.clickCell = this.clickCell.bind(this);
        this.init = this.init.bind(this);
        this.callbackDiOnMessage = this.callbackDiOnMessage.bind(this);
    }

    callbackDiOnMessage(event){
        const response = JSON.parse(event.data);
        if (response.turn === 'yes') {
            this.setState({ turn: "yes", title: "Ora è il tuo turno" });
        } else if (response.turn === 'no') {
            this.setState({ turn: "no", title: "Non è il tuo turno" });
        } else if (response.turn === 'won') {
            this.setState({ turn: "won", title: "Complimenti hai vinto!" });
        } else if (response.turn === 'lost') {
            this.setState({ turn: "lost", title: "Peccato hai perso!" });
        } else if (response.turn === 'tie') {
            this.setState({ turn: "tie", title: "Non ha vinto nessuno " });
        }
        var newTable = response.table.split(",");
        this.setState({ table: newTable });

        console.log("Pre turn e state: " + this.state.turn + " " + this.state.title);
    }

    init(){
        console.log("INIZIO INIT");
        this.socket = new WebSocket("ws://localhost:8080/EsSim260122_3/tris");
        this.socket.onmessage = this.callbackDiOnMessage;
    }
    


    clickCell(e) {
		var cell = e.target.getAttribute("name");
		var currentVal = this.state.table[cell];
        if (this.state.turn !== "yes" || currentVal == "X" || currentVal == "O") {
            alert("We nino, non tocca mica a te eh!");
        } else {
            console.log("Premuto: " + cell);
            var toSend = Math.trunc(cell / 3) + "," + cell % 3;
            this.socket.send(toSend);
            console.log("Sent: " + toSend);
        }
    }

    render() {
        return (

            <div className="tictactoe-body">
                <button onClick={this.init}>Fai partire tutta la baracca da qua</button>
                <h1>Tic Tac Toe</h1>
                <h2>{this.state.title}</h2>
                <table style={{ border: "1px solid black" }}>
                    <tr>
                        <td name="0" onClick={this.clickCell} style={{ width: "45px", height: "45px", border: "1px solid black" }}>{this.state.table[0]}</td>
                        <td name="1" onClick={this.clickCell} style={{ width: "45px", height: "45px", border: "1px solid black" }}>{this.state.table[1]}</td>
                        <td name="2" onClick={this.clickCell} style={{ width: "45px", height: "45px", border: "1px solid black" }}>{this.state.table[2]}</td>
                    </tr>
                    <tr>
                        <td name="3" onClick={this.clickCell} style={{ width: "45px", height: "45px", border: "1px solid black" }}>{this.state.table[3]}</td>
                        <td name="4" onClick={this.clickCell} style={{ width: "45px", height: "45px", border: "1px solid black" }}>{this.state.table[4]}</td>
                        <td name="5" onClick={this.clickCell} style={{ width: "45px", height: "45px", border: "1px solid black" }}>{this.state.table[5]}</td>
                    </tr>
                    <tr>
                        <td name="6" onClick={this.clickCell} style={{ width: "45px", height: "45px", border: "1px solid black" }}>{this.state.table[6]}</td>
                        <td name="7" onClick={this.clickCell} style={{ width: "45px", height: "45px", border: "1px solid black" }}>{this.state.table[7]}</td>
                        <td name="8" onClick={this.clickCell} style={{ width: "45px", height: "45px", border: "1px solid black" }}>{this.state.table[8]}</td>
                    </tr>
                </table>
            </div>

        );
    }
}

ReactDOM.render(<App />, document.getElementById("root"));