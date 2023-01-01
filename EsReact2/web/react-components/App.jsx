'use strict';


class Lavagna extends React.Component {
    constructor() {
        super();
    }

    
    render() {
        return (
            <div name="formBody">
                <textarea name="myTextArea" placeholder="Dati complessivi" rows="5" cols="32" style={{border:"1px"}} value={this.props.myText}>
                </textarea>
                <button onClick={this.props.resetText}>Resetta tutto</button>
            </div>
        );
    }
}


class FormIscrizione extends React.Component {
    constructor() {
        super();
        this.onClick = this.onClick.bind(this);
    }

    validEmail(myField) {
        var result = false;
        if ( nonBlank(myField) ) {
            var tempstr = new String(myField.value);
            var aindex = tempstr.indexOf("@");
            if (aindex > 0 ) {
                var pindex = tempstr.indexOf(".",aindex);
                    if ( (pindex > aindex+1) && (tempstr.length > pindex+1) ) {
                    result = true;
                } else {
                    result = false;
                }
            }
        }
        if (!result) {
            alert("Please enter a valid email address in the form: yourname@yourdomain.com");
            myField.focus();
        }
        return result;
    }

    onClick(e) {
        var email = document.getElementsByName("email")[0].value;
        var pw = document.getElementsByName("password")[0].value;
        var nation = document.getElementsByName("nazione")[0].value;
        if(email !== "" && pw !== "" && nation !== ""){
            this.props.moveDataFunct(email, pw, nation);
        }else{
            alert("Inserisci valori validi!");
        }

    };


    
    render() {
        return (

            <div name="formBody">
                    <input type="email" name="email" placeholder="email"></input>
                    <input type="password" name="password" placeholder="password"></input>    
                    <p>Inserisci una nazione</p>
                    <select name="nazione">
                        <option value="italia">Italia</option>
                        <option value="francia">Francia</option>
                        <option value="spagna">Spagna</option>
                        <option value="germania">Germania</option>
                    </select>
                    <button onClick={this.onClick}>Clicca per inviare</button>
            </div>

        );
    }
}




class App extends React.Component {
    constructor(props) {
        super(props);
        this.moveDataFunct = this.moveDataFunct.bind(this);
        this.resetText = this.resetText.bind(this);
        this.state = {text:""};
        
    }

    moveDataFunct(newEmail, newPw, newNat){
        var currentText = this.state.text
        this.setState({text:(this.state.text+"\n"+newEmail+" "+newPw+" "+newNat)});
    }

    resetText(){
        this.setState({text:""});
    }

    render() {
        return (
            <div name="myApp">
                <FormIscrizione moveDataFunct={this.moveDataFunct}/>
                <Lavagna myText={this.state.text} resetText={this.resetText}/>
            </div>
        );
    }
}
ReactDOM.render(<App/>, document.getElementById("root"));

