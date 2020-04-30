import React, {Component} from 'react';
import ProjectDetails from "./ProjectDetails";
import axios from 'axios'

export default class TestComponent extends Component {
    state = {
        uid: "react test"
    };

    componentDidMount() {
        axios.get("http://localhost:8080/api/uid").then(response => {
            this.setState({uid: response.data})
        })
    }

    render() {
        return (
            <div>
                <ProjectDetails val='1'/>
                <ProjectDetails val='2'/>
        User ID: {this.state.uid}

            </div>
        );
    }
}