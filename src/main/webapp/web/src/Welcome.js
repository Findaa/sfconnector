import React from 'react';
import Panel from "react-bootstrap/lib/Panel";

export const Welcome = () => {
    return (
        <div>
            <div className="customerdetails">
                <Panel bsStyle="info" className="centeralign">
                    <Panel.Heading>
                        <Panel.Title componentClass="h3">Strona Główna</Panel.Title>
                    </Panel.Heading>
                    <Panel.Body>
                        <p>Aby przejśc do aplikacji, zaloguj się w ustawieniach</p>
                        <p>Logowanie aktywne przy pomocy salesforce na środowisku mcopue.dev</p>
                    </Panel.Body>
                </Panel>
            </div>

        </div>
    )
}