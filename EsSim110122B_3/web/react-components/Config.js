'use strict';
//Props needed: setConfigInfo
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
        if(e.target.name === "d"){
            console.log("Settao d a "+e.target.value);
            this.setState({d:e.target.value});
        }else if(e.target.name === "passi"){
            if(e.target.value > 3){
                console.log("Settao passi a "+e.target.value);
                this.setState({passi:e.target.value});
            }
        }
    }

    configOnsubmit(e){
        e.preventDefault();
        console.log("settato tutto  ");
        this.props.setConfigInfo(this.state.d, this.state.passi);                   //Props: setConfigInfo(d, passi)
        e.target.setAttribute("disabled", "");
    }

    render() {
        return (

            <div className="config-body">
                <h1>Configurazione</h1>
                <form onSubmit={this.configOnsubmit}>
                    <input style="text" name="d" onChange={this.configOnclick} placeholder="inserisci d"></input>
                    <input style="text" name="passi" onChange={this.configOnclick} placeholder="inserisci passi"></input>
                    <input type="submit" value="Submit" />
                </form>
            </div>

        );
    }
}

