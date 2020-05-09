import React, {Component} from 'react';
import Tile from "../tile/Tile";
import axios from 'axios'

export default class TestComponent extends Component {
    state = {
        uid: "react test"
    };

    componentDidMount() {
        if(this.state.uid){
            this.setState({uid: "Error loading Id"})
        }
        axios.get("http://localhost:8080/api/uid").then(response => {
            console.log(response.data)
            this.setState({uid: response.data});
        });
    }

    render() {
        return (
            <div>
                <Tile val='1'/>
                <Tile val='2'/>

        User ID: {this.state.uid}

            </div>
        );
    }
}