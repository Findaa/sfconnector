import React, {Component} from 'react';
import ProjectDetails from "./ProjectDetails";

export default class TestComponent extends Component {
    render() {
        return (
            <div>
                <ProjectDetails val='1'/>
                <ProjectDetails val='2'/>
            </div>
        );
    }
}