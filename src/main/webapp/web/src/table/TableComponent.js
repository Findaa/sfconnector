import React, {Component} from "react";

export default class TableComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {data: []}
    }

    componentDidMount() {
        this.loadData()
    }

    loadData() {
        fetch('http://localhost:8080/api/opportunities')
        // fetch('assets/samplejson/project1.json')
            .then(response => response.json())
            .then(data => {
                console.log("should print here: ");
                console.log(data);
                this.setState({data: data})
            })
            .catch((err) => {
                console.error(this.props.url, err.toString())
                console.log("no printerino")
            })

    }

    render() {
        return (
            <div>
                Data: {this.state.data}
            </div>
        )
    }

}