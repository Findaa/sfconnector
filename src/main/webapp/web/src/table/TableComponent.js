import React, {Component} from "react";
import axios from 'axios'

export default class TableComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            data: []
        }
    }

    componentDidMount() {
        this.loadData()
    }

    loadData() {
        fetch('http://localhost:8080/api/opportunities')
            .then(response => response.json())
            .then(data =>{
                console.log("data");
                console.log(data)

            })
            .catch((err) => {
                console.error(this.props.url, err.toString());
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