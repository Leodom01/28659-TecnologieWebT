'use strict';

class MyComponent extends React.Component {
    constructor() {
        super();
        this.state = {
            result: ""
        }
        this.onClick = this.onClick.bind(this);
    }

    onClick(e) {
        let button = e.target.name
        if (button === "=") {

            this.calculate()
        }

        else if (button === "C") {
            this.reset()
        }
        else if (button === "CE") {
            this.backspace()
        }

        else {
            this.setState({
                result: this.state.result + button
            })
        }
    };


    calculate() {
        try {
            this.setState({
                result: (eval(this.state.result) || "") + ""
            })
        } catch (e) {
            this.setState({
                result: "error"
            })

        }
    };

    reset() {
        this.setState({
            result: ""
        })
    };


    backspace() {
        this.setState({
            result: this.state.result.slice(0, -1)
        })
    };

    render() {
        return (

            <div className="calculator-body">
                <h1>Calcolatrice</h1>
                <Display result={this.state.result} />
                <KeyBoard onClick={this.onClick} />
            </div>

        );
    }
}
