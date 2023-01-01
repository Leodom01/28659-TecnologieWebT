'use strict';

class Config extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            d:"",
            passi:""
        };
        this.configOnclick = this.configOnclick.bind(this);
        this.configOnsubmit = this.configOnsubmit.bind(this);
    }

    configOnclick(e){
        if(e.target.getAttribute("name") === "d"){
            console.log("Settao d a "+e.target.value);
            this.setState({d:e.target.value});
        }else if(e.target.getAttribute("name") === "passi"){
            if(e.target.value > 3){
                console.log("Settao passi a "+e.target.value);
                this.setState({passi:e.target.value});
            }
        }
    }

    configOnsubmit(e){
        e.preventDefault();
        console.log("settato tutto  "+this.state);
        this.props.setConfigInfo(this.state.d, this.state.passi);                   //Props: setConfigInfo(d, passi)
        e.target.setAttribute("disabled", "");
    }

    render() {
        return (

            <div className="config-body">
                <h1>Configurazione</h1>
                <form onSubmit={this.configOnsubmit}>
                    <input type="text" name="d" onChange={this.configOnclick} placeholder="inserisci d"></input>
                    <input type="text" name="passi" onChange={this.configOnclick} placeholder="inserisci passi"></input>
                    <input type="submit" value="Submit" />
                </form>
            </div>

        );
    }
}

class Griglia extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            passiDone : 0,
            points: 0
        };
        this.clickedCell = this.clickedCell.bind(this);
    }


    clickedCell(e){
        console.log("Cliccata: "+e.target.getAttribute("name"));
        if(e.target.getAttribute("name") === this.props.mine1.toString() || e.target.getAttribute("name") === this.props.mine2.toString()){
            //Mina presa
            e.target.style.backgroundColor = "red";
            var passi = this.state.passiDone+1;
            this.setState({passiDone:passi});
            this.props.updateData(false, this.state.passiDone);                                                 //props updateDate(completed : bool, punti:int)
            alert("Partita terminata: sei morto!");
        }else{
            //Mina non presa
            e.target.style.backgroundColor = "blue";
            var newPunti = this.state.points+5;
            this.setState({points: newPunti});
            var passi = this.state.passiDone+1;
            this.setState({passiDone:passi});
        }

        if(this.state.passiDone === parseInt(this.props.passi)){
            this.props.updateData(true, this.state.passiDone);      
            alert("Partita completata: sei vivo!");
        }
        console.log("Passi ad ora: "+this.state.passiDone);
    }

    render() {
        var myTab = [];
        for(var i = 0; i<this.props.d; i++){
            var row = []
            for(var j = 0; j<this.props.d; j++){
                var thisName = eval(i*this.props.d+j);
                row.push(<td name={thisName} width="25px" height="25px" style={{background: "yellow"}} onClick={this.clickedCell}></td>)
            }
            myTab.push(<tr>{row}</tr>);
        }
        return (
            <div className="griglia-body">
                <h1>Griglia</h1>
                <table>
                    {myTab}
                </table>
            </div>

        );
    }
}

class Conteggio extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (

            <div className="conteggio-body">
                <h1>Conteggio</h1>
                <p>Percorsi totali: {this.props.percorsiTotali}</p>
                <p>Percorsi completati: {this.props.percorsiCompletati}</p>
                <p>Punti totali: {this.props.punti}</p>
            </div>

        );
    }
}

class App extends React.Component {
    constructor() {
        super();
        this.state = {
            d:0,
            passi:0,
            punti:0,
            percorsiTot:0,
            percorsiCompletati:0, 
            mine1: 0, 
            mine2: 0
        };
        this.setConfigInfo = this.setConfigInfo.bind(this);
        this.updateData = this.updateData.bind(this);
    }
    
    setConfigInfo(schema, passiMax){
        this.setState({d:schema, 
                        passi:passiMax});
        console.log("App: schema e passi max: "+schema+" "+passiMax);
        var newM1 = Math.floor(Math.random()*(schema*schema));
        var newM2 = Math.floor(Math.random()*(schema*schema));
        this.setState({mine1:newM1,
                        mine2:newM2});
    }

    updateData(gotCompleted, puntiFatti){
        var newCompleti = this.state.percorsiCompletati;
        var newPercorsiTot = this.state.percorsiTot+1;
        var newPunti = this.state.punti+puntiFatti;
        if(gotCompleted){
            newCompleti++;
        }
        this.setState({percorsiCompletati: newCompleti,
                        percorsiTot: newPercorsiTot,
                        punti: newPunti});
    }

    render() {
        return (
            <div className="App-body">
                <h1>Campo minato</h1>
                <Config setConfigInfo={this.setConfigInfo}/>
                <Griglia d={this.state.d} passi={this.state.passi} mine1={this.state.mine1} mine2={this.state.mine2} updateData={this.updateData}/>
                <Conteggio percorsiTotali={this.state.percorsiTot} percorsiCompletati={this.state.percorsiCompletati} punti={this.state.punti}/>
            </div>

        );
    }
}

ReactDOM.render(<App />, document.getElementById("root"));
