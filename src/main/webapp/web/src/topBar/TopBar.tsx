import React, {useEffect, useState} from 'react';
// @ts-ignore
import MenuItem from "react-bootstrap/lib/MenuItem";
import axios from "axios";
// @ts-ignore
import DropdownButton from "react-bootstrap/lib/DropdownButton";
// @ts-ignore
import _JSXStyle from 'styled-jsx/style'
// @ts-ignore
import Center from 'react-center';
import Button from "@material-ui/core/Button";
import ButtonGroup from "@material-ui/core/ButtonGroup";
// @ts-ignore
import {connect} from 'react-redux'

function TopBar(props: any) {
    const [authOk, setAuthOk] = useState(false);
    let [open, setOpen] = useState(false);
    useEffect(() => {
            console.log("Checked Auth in Did Mount");
            if (props.authOk === false) checkAuthorization(authOk);
        }
    );

    const checkAuthorization = (authOk: boolean) => {
        if (props.authOk === false || !props.authOk || authOk === null || authOk === undefined) {
            axios.get("http://localhost:8080/api/issfauthorized")
                .then(response => {
                    setAuthOk(response.data);
                    props.onVerifyLogin(response.data)
                })
            console.log("checked auth and got: " + authOk)
            console.log("with props auth: " + props.authOk)
        }
    }

    const logout = () => {
        setAuthOk(false);
        props.logout()
    };

    const openAbout = () => {
        setOpen(!open)
    }

    const unauthorizedMenu = <div>
        <_JSXStyle id="button">{`DropdownButton.jsx-123 {color: background: #117a8b;}`}</_JSXStyle>
        <ButtonGroup size="large" color="primary" aria-label="large outlined primary button group">
            <Button
                size="small">
                <DropdownButton title='About'>
                    <MenuItem href='/motivation'>Project Motivation</MenuItem>
                    <MenuItem href='/members'>Project Members</MenuItem>
                    <MenuItem href='/flow'>Data flow</MenuItem>
                    <MenuItem href='/res'>Resources</MenuItem>
                    <MenuItem href='/projects'>Test template</MenuItem>
                    <MenuItem divider/>
                    <MenuItem href='/test' active={true}>Test API after login</MenuItem>
                </DropdownButton>
            </Button>
            <Button
                size="small">
                <DropdownButton bsStyle='light' title='Settings'>
                    <MenuItem href='/login'>Login</MenuItem>
                    <MenuItem href='/calendar'>Calendar</MenuItem>
                    <MenuItem divider/>
                </DropdownButton>
            </Button>
        </ButtonGroup>
    </div>

    const authorizedMenu = <div>
        <_JSXStyle id="button">{`DropdownButton.jsx-123 {color: background: #117a8b;}`}</_JSXStyle>
        <ButtonGroup size="large" color="primary" aria-label="large outlined primary button group">
            <Button
                size="small">
                <DropdownButton title='About'>
                    <MenuItem href='/motivation'>Project Motivation</MenuItem>
                    <MenuItem href='/members'>Project Members</MenuItem>
                    <MenuItem href='/flow'>Data flow</MenuItem>
                    <MenuItem href='/res'>Resources</MenuItem>
                    <MenuItem href='/projects'>Test template</MenuItem>
                    <MenuItem divider/>
                    <MenuItem href='/test' active={true}>Test API after login</MenuItem>
                </DropdownButton>
            </Button>
            <Button
                size="small">
                <DropdownButton bsStyle='light' title='Settings'>

                    <MenuItem href='/calendar'>Calendar</MenuItem>
                    <MenuItem divider/>
                    <MenuItem href="http://localhost:8080/logout" onClick={logout}>Logout</MenuItem>
                </DropdownButton>
            </Button>
            <Button
                size="small">
                <DropdownButton bsStyle='light' title='Services'>
                    <MenuItem href='/plot'>Plots</MenuItem>
                    <MenuItem href='/test'>Tests</MenuItem>
                    <MenuItem href='/analytics'>Tables</MenuItem>
                </DropdownButton>
            </Button>
        </ButtonGroup>
    </div>

    let buttonGroup = props.authOk === true ? authorizedMenu : unauthorizedMenu

    return (
        <div>
            <Center>
                <div className="jsx-button">
                    {buttonGroup}
                </div>
            </Center>
        </div>
    );
}

const mapStateToProps = (state: any) => {
    return {
        authOk: state.authOk
    }
}

const mapDispatchToProps = (dispatch: any) => {
    return {
        onVerifyLogin: (authOk: boolean) => dispatch({type: 'VERIFY_AUTH', authOk: authOk}),
        logout: () => {
            console.log("trying to dispatch logout")
            dispatch({type: 'LOGOUT'})
        }
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(TopBar)