import React, {Component} from 'react';
import Panel from "react-bootstrap/lib/Panel";

export default class Members extends Component {

    render(){
        return(
            <div>
                <div className="customerdetails">
                    <Panel bsStyle="info" className="centeralign">
                        <Panel.Heading>
                            <Panel.Title componentClass="h3">Name 1</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>
                            <p>Member 1</p>
                            <p>Info</p>
                        </Panel.Body>
                    </Panel>
                </div>
                <div className="customerdetails">
                    <Panel bsStyle="info" className="centeralign">
                        <Panel.Heading>
                            <Panel.Title componentClass="h3">Name 2</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>
                            <p>Member 1</p>
                            <p>Info</p>
                        </Panel.Body>
                    </Panel>
                </div>
            </div>
        );
    }
}