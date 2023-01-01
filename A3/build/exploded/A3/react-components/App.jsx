'use strict';

//Vuole prop newLancio
class LancioDadi extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
           num1: "",
           num2: "", 
           num3: "" , 
           col1: "",
           col2: "",
           col3: ""
        };
        this.btnClicked = this.btnClicked.bind("this");
    }

    btnClicked(e){
        var val1 = 1+Math.floor(Math.random()*6);
        var val2 = 1+Math.floor(Math.random()*6);
        var val3 = 1+Math.floor(Math.random()*6);
        var newcol1 = (val1<=3)?"red":"green";
        var newcol2 = (val1<=3)?"red":"green";
        var newcol3 = (val1<=3)?"red":"green";
        this.setState({num1: val1, 
                    num2: val2, 
                    num3: val3, 
                    col1: newcol1, 
                    col2: newcol2, 
                    col3: newcol3});
        this.props.newLancio(val1, val2, val3);
    }

    render() {
        return (
            <div className="lancioDadi-body">
                <h1>Lancio dei dadi! Venghino signori venghino!</h1>
                <button onClick={this.btnClicked()}>Clicca per lanciare</button>
                <input type="text" readonly value={this.state.num1} style={{background: this.state.col1}}></input>
                <input type="text" readonly value={this.state.num2} style={{background: this.state.col2}}></input>
                <input type="text" readonly value={this.state.num3} style={{background: this.state.col3}}></input>
            </div>
        );
    }
}

//props: lanci nella forma X1 X2 X3,Y1 Y2 Y3...
class SequenzaLanci extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        var elementilista = [];
        if(this.props.lanci === ""){
            elementilista.push(<li>Nesun elemento disponibile</li>);
        }else{
        for(var tripletta of this.props.lanci.split(",")){
            var lancio1 = tripletta.split(" ")[0];
            var lancio2 = tripletta.split(" ")[1];
            var lancio3 = tripletta.split(" ")[2];
            elementilista.push(<li>Dado 1: {lancio1} - Dado 2: {lancio2} - Dado 3: {lancio3} -</li>)
        }
        }
        
        return (
            <div className="sequenzaLanci-body">
                <h1>Ecco i lanci buoni di lor signori:</h1>
                <ul>
                    {elementilista}
                </ul>    
            </div>

        );
    }
}

//props: resClicked(), inlista, fuorilsita nella forma di prima
class Statistica extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          maxSum:"",
          minSum:"",
          avgFuoriLista:""  
        };
        this.btnClicked = this.btnClicked.bind(this);
    }

    btnClicked(){
        if(this.props.inlista === ""){
            this.setState({maxSum: "nessun valore disponibile"});
            this.setState({minSum: "nessun valore disponibile"});
        }else{
            //Fill maxSum
        var max = -1;
        for(var tripletta of this.props.inlista.split(",")){
            var valori = tripletta.split(" ");
            var sum = eval(valori[0])+eval(valori[1])+eval(valori[2]);
            if(sum>max){
                max = sum;
            }
        }
        this.setState({maxSum: max});
        //Fill min sum
        var min = Number.MAX_SAFE_INTEGER;
        for(var tripletta of this.props.inlista.split(",")){
            var valori = tripletta.split(" ");
            if(sum<min){
                min = sum;
            }
        }
        this.setState({minSum: min});
        }
        
        
        if(this.props.fuorilista === ""){
            this.setState({avgFuoriLista: "nessun valore disponibile"});
        }else{
            //Fill out of list avg
        var somma = 0;
        var valori = 0;
        for(var tripletta of this.props.fuorilista.split(",")){
            var valori = tripletta.split(" ");
            somma += eval(valori[0])+eval(valori[1])+eval(valori[2]);
            valori++;
        }
        var theAvg = somma/valori;
        this.setState({avgFuoriLista: theAvg});
        }
        
    }


    render() {
        return (

            <div className="sequenzaLanci-body">
                <button onClick={this.btnClicked()}>Carica valori</button>
                <input type="text" readonly value={this.state.maxSum}></input>
                <input type="text" readonly value={this.state.minSum}></input>
                <input type="text" readonly value={this.state.avgFuoriLista}></input>
                <button onClick={this.props.resClicked()}>Pulisci tutto</button>
            </div>

        );
    }
}

class App extends React.Component {
    constructor() {
        super();
        this.state = {
            inlista: "",
            fuorilista : ""
        };
        this.newLancio = this.newLancio.bind(this);
        this.resClicked = this.resClicked.bind(this);
    }

    newLancio(val1, val2, val3){
        var sum = val1+val2+val3;
        if(sum <= 15 && sum >= 6){
            //Lancio in lista lanci
            var newLanci = this.state.inlista+","+val1+" "+val2+" "+val3;
            this.setState({inlista: newLanci});
        }else{
            //Lanci in fuori lista
            var newLanci = this.state.fuorilista+","+val1+" "+val2+" "+val3;
            this.setState({fuorilista: newLanci});
        }
    }

    resClicked(){
        this.setState({inlista: "", 
                        fuorilista: ""});
    }

    render() {
        return (

            <div className="app-body">
                <LancioDadi newLancio={this.newLancio}></LancioDadi>
                <SequenzaLanci lanci={this.state.inlista}></SequenzaLanci>
                <Statistica resClicked={this.resClicked} inlista={this.state.inlista} fuorilista={this.state.inlista}></Statistica>
            </div>

        );
    }
}

ReactDOM.render(<App />, document.getElementById("root"));
