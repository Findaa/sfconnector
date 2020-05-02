import React, {Component, useState, useEffect} from 'react';
import TopBarButton from "./TopBarButton.js";
import DropdownButton from "react-bootstrap/lib/DropdownButton";
import MenuItem from "react-bootstrap/lib/MenuItem";
import axios from "axios";
import Button from '@material-ui/core/Button';
import Menu from '@material-ui/core/Menu';
import Fade from '@material-ui/core/Fade';

export default class TopBar extends Component {
    state = {
        authOk: false,
        anchorEl: null,
        open: Boolean(this.state.anchorEl)
    };

    barRedirect = (endpoint) => console.log("barRedirect: " + endpoint);

    checkAuthorization = () => {
        axios.get("http://localhost:8080/api/issfauthorized").then(response => {
            this.setState({authOk: response.data});
        });
    };

     handleClick = (event) => {
        this.setState({anchorEL: event.currentTarget});
    };

     handleClose = () => {
         this.setState({anchorEL: null});
    };

    componentDidMount() {
        if (!this.state.authOk) {
            this.checkAuthorization();
        }
    }

    logout() {
        axios.post("http://localhost:8080/logout")
    };

    shouldComponentUpdate(nextProps, newState) {
        return this.props.name === nextProps.name;
    }

    render() {
        return (
            <div>
                <div>
                    <Button aria-controls="fade-menu" aria-haspopup="true" onClick={this.handleClick}>
                        About
                        <Menu
                            id="fade-menu"
                            anchorEl={this.anchorEl}
                            keepMounted
                            open={open}
                            onClose={this.handleClose}
                            TransitionComponent={Fade}
                        >
                        <MenuItem onClick={this.handleClose} href='/motivation'>Project Motivation</MenuItem>
                        <MenuItem onClick={this.handleClose} href='/members'>Project Members</MenuItem>
                        <MenuItem onClick={this.handleClose} href='/flow'>Data flow</MenuItem>
                        <MenuItem onClick={this.handleClose} href='/res'>Resources</MenuItem>
                        <MenuItem onClick={this.handleClose} href='/projects'>Test template</MenuItem>
                        <MenuItem onClick={this.handleClose} divider/>
                        <MenuItem onClick={this.handleClose} href='/test' active={true}>Test API after login</MenuItem>
                        </Menu>
                    </Button>
                    {this.state.authOk ?
                        <DropdownButton bsStyle='secondary' title='Services'>
                            <MenuItem href='/plot'>Plots</MenuItem>
                            <MenuItem href='/test'>Tests</MenuItem>
                            <MenuItem href='/table'>Tables</MenuItem>
                        </DropdownButton>
                        : null}
                    <DropdownButton bsStyle='secondary' title='Settings'>
                        <MenuItem href='/login'>Login</MenuItem>
                        <MenuItem href='/calendar'>Calendar</MenuItem>
                        <MenuItem divider/>
                        {this.state.authOk ?
                            <MenuItem href="http://localhost:8080/logout">Logout</MenuItem>
                            : null
                        }
                    </DropdownButton>
                    <TopBarButton name="Functional Button Test" action={this.barRedirect} endpoint="/testEndpoint"/>
                </div>
            </div>
        );
    }
}
