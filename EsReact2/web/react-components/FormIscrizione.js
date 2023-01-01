'use strict';

class FormIscrizione extends React.Component {
    constructor() {
        super();
        this.onClick = this.onClick.bind(this);
        const socket = new WebSocket("ws://localhost:8080/EsReact2/lavagna");
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
        var email = e.target.email.value;
        var pw = e.target.password.value;
        var nation = e.target.nazione.value;
        if(this.validEmail(e.target.email) && pw !== "" && nation !== ""){
            this.props.moveDataFunct(email, pw, nation);
        }else{
            alert("Inserisci valori validi!");
        }

    };


    
    render() {
        return (

            <div className="formBody">
                <form>
                    <input type="email" name="email" placeholder="email"></input>
                    <input type="password" name="password" placeholder="password"></input>    
                    <p>Inserisci una nazione</p>
                    <select name="nazione">
                        <option value="italia">Italia</option>
                        <option value="francia">Francia</option>
                        <option value="spagna">Spagna</option>
                        <option value="germania">Germania</option>
                    </select>
                    <button onclick={this.onClick}>Clicca per inviare</button>
                </form>
            </div>

        );
    }
}
