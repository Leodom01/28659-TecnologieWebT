'use strict';
//props required: d, passi, updateData
class Griglia extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            color : new Array(this.props.d*this.props.d),
            mines : [Math.floor(Math.random()*(this.props.d*this.props.d)), Math.floor(Math.random()*(this.props.d*this.props.d))],
            passiDone : 0,
            points: 0
        };
        this.clickedCell = this.clickedCell.bind(this);
        this.initGrid = this.initGrid.bind(this);
        this.initGrid();
    }

    initGrid(){
        var newColors = new Array(this.props.d*this.props.d).fill("yellow");
        this.setState({colors:newColors});
        this.setState({mines:[Math.floor(Math.random()*(this.props.d*this.props.d)), Math.floor(Math.random()*(this.props.d*this.props.d))]});
        this.setState({passiDone:0});
        this.setState({points:0});
    }

    clickedCell(e){
        if(this.state.mines.includes(e.target.name)){
            //Mina presa
            var newColors = thi.state.color.map((c, i) => { (i===e.target.name)?"red":c;});
            this.setState({color:newColors});
            var passi = this.state.passiDone+1;
            this.setState({passiDone:passi});
            this.props.updateData(false, this.state.passiDone);                                                 //props updateDate(completed : bool, punti:int)
            alert("Partita terminata: sei morto!");
            this.initGrid();
        }else{
            //Mina non presa
            var newColors = thi.state.color.map((c, i) => { (i===e.target.name)?"blue":c;});
            this.setState({color:newColors});
            var newPunti = this.state.points+5;
            this.setState({points: newPunti});
            var passi = this.state.passiDone+1;
            this.setState({passiDone:passi});
        }

        if(this.state.passiDone === this.props.passi){
            this.props.updateData(true, this.state.passiDone);      
            alert("Partita completata: sei vivo!");
            this.initGrid();
        }
    }

    render() {
        var myTab = [];
        for(var i = 0; i<this.props.d; i++){
            var row = []
            for(var j = 0; j<this.props.d; j++){
                var thisName = eval(i*10+j);
                row.push(<td name={thisName} width="25px" height="25px" color={this.state.color[thisName]} onClick={this.clickedCell}></td>)
            }
            myTab.push(<tr>{row}</tr>);
        }
        return (
            <div className="griglia-body">
                <h1>Griglia</h1>
                <table>

                </table>
            </div>

        );
    }
}

