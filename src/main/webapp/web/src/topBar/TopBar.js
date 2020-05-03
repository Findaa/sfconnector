import React, {PureComponent} from 'react';
import TopBarButton from "./TopBarButton.js";
import MenuItem from "react-bootstrap/lib/MenuItem";
import axios from "axios";
import DropdownButton from "react-bootstrap/lib/DropdownButton";

export default class TopBar extends PureComponent {
    state = {
        authOk: false
    };

    barRedirect = (endpoint) => console.log("barRedirect: " + endpoint);

    shouldComponentUpdate(nextProps, nextState) {
        return this.state.authOk !== nextState.authOk;
    }
    checkAuthorization = () => {
        if (!this.state.authOk) {
            axios.get("http://localhost:8080/api/issfauthorized").then(response => {
                this.setState({authOk: response.data});
            });
            console.log("checked auth and got: " + this.state.authOk)
            this.setState({state: this.state});
        }
    };

    componentDidMount() {
        console.log("Checked Auth in Did Mount");
        this.checkAuthorization();
        this.setState({state: this.state});
    }


    logout() {
        axios.post("http://localhost:8080/logout");
        this.setState({state: this.state});
    };

    render() {
        return (
            <div>
                <div>
                    <DropdownButton bsStyle='light' title='About'>
                        <MenuItem href='/motivation'>Project Motivation</MenuItem>
                        <MenuItem href='/members'>Project Members</MenuItem>
                        <MenuItem href='/flow'>Data flow</MenuItem>
                        <MenuItem href='/res'>Resources</MenuItem>
                        <MenuItem href='/projects'>Test template</MenuItem>
                        <MenuItem divider/>
                        <MenuItem href='/test' active={true}>Test API after login</MenuItem>
                    </DropdownButton>

                    <DropdownButton bsStyle='light' title='Settings'>
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
                    {this.state.authOk ?
                        <DropdownButton bsStyle='light' title='Services'>
                            <MenuItem href='/plot'>Plots</MenuItem>
                            <MenuItem href='/test'>Tests</MenuItem>
                            <MenuItem href='/table'>Tables</MenuItem>
                        </DropdownButton>
                        : null}
                    <TopBarButton name="Functional Button Test" action={this.barRedirect} endpoint="/testEndpoint"/>
                </div>
            </div>
        );

    }
}