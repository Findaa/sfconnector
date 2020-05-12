import * as React from 'react'

// @ts-ignore
import Panel from "react-bootstrap/lib/Panel"
import Ryj from './ryj.png'

export default function Members() {

    return (
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
                        <p style={{fontSize: '14px', fontWeight: 'bold'}}>
                            <img src={Ryj} style={{width: '4%', height: '4%', border: '2px'}} alt="Face"/>
                            <div>{"\n"}{"\n"}{"\n"} Michał Cop</div>
                        </p>
                        <p>Info</p>
                    </Panel.Body>
                </Panel>
            </div>
        </div>
    )
}