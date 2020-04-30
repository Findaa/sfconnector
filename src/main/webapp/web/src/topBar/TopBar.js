import React, {Component, useState, useEffect} from 'react';
import TopBarButton from "./TopBarButton.js";
import DropdownButton from "react-bootstrap/lib/DropdownButton";
import MenuItem from "react-bootstrap/lib/MenuItem";

export default class TopBar extends Component {

    barRedirect = (endpoint) => console.log("barRedirect: " + endpoint);

    render() {
        return (
            <div>
                <div>
                    <DropdownButton bsStyle='secondary' title='About Project'>
                        <MenuItem href='/members'>About members</MenuItem>
                        <MenuItem href='/projects'>Project Description</MenuItem>
                        <MenuItem divider/>
                        <MenuItem eventKey='3' active={true}>University of Economics Site</MenuItem>
                    </DropdownButton>
                    <DropdownButton bsStyle='secondary' title='Organization / Login test'>
                        <MenuItem href='/login'>Login</MenuItem>
                        <MenuItem href='/calendar'>Calendar</MenuItem>

                        <MenuItem divider/>
                        <MenuItem eventKey='4'>Logout</MenuItem>
                    </DropdownButton>
                    <TopBarButton name="Functional Button Test" action={this.barRedirect} endpoint="/testEndpoint"/>
                </div>
            </div>
        );
    }
}
