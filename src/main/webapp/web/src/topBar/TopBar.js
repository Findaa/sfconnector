import React, {Component, useState, useEffect} from 'react';
import TopBarButton from "./TopBarButton.js";
import DropdownButton from "react-bootstrap/lib/DropdownButton";
import MenuItem from "react-bootstrap/lib/MenuItem";
import axios from "axios";

export default class TopBar extends Component {
    state = {
        authOk: false
    };

    barRedirect = (endpoint) => console.log("barRedirect: " + endpoint);

    checkAuthorization = () => {
        axios.get("http://localhost:8080/api/issfauthorized").then(response => {
            this.setState({authOk: response.data});
        });
        console.log("checked auth and got: " + this.state.authOk)
    };

    componentDidMount() {
        if (!this.state.authOk) {
            console.log("Checked auth did mount");
            this.checkAuthorization();
        }
    }

    shouldComponentUpdate(nextProps, newState) {
        return this.props.name === nextProps.name;
    }

    logout() {
        axios.post("http://localhost:8080/logout")
    };

    render() {
        return (
            <div>
                <div>
                    <DropdownButton bsStyle='secondary' title='About'>
                        <MenuItem href='/motivation'>Project Motivation</MenuItem>
                        <MenuItem href='/members'>Project Members</MenuItem>
                        <MenuItem href='/flow'>Data flow</MenuItem>
                        <MenuItem href='/res'>Resources</MenuItem>
                        <MenuItem href='/projects'>Test template</MenuItem>
                        <MenuItem divider/>
                        <MenuItem href='/test' active={true}>Test API after login</MenuItem>
                    </DropdownButton>
                    {this.state.authOk ?
                        <DropdownButton bsStyle='secondary' title='Services'>
                            <MenuItem href='/plot'>Plots</MenuItem>
                            <MenuItem href='/test'>Tests</MenuItem>
                            <MenuItem href='/table'>Tables</MenuItem>
                        </DropdownButton>
                        : null}
                    <DropdownButton bsStyle='secondary' title='Settings'>
                        {!this.state.authOk
                            ? <MenuItem href='/login'>Login</MenuItem>
                            : null}
                        <MenuItem href='/calendar'>Calendar</MenuItem>
                        <MenuItem divider/>
                        {this.state.authOk
                            ? <MenuItem href="http://localhost:8080/logout">Logout</MenuItem>
                            : null
                        }
                    </DropdownButton>
                    <TopBarButton name="Functional Button Test" action={this.barRedirect} endpoint="/testEndpoint"/>
                </div>
            </div>
        );

    }
}