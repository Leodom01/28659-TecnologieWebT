'use strict';
//Props needed:percorsiTotali, percorsiCompletati, punti
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

