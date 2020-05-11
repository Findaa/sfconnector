import React, {useEffect, useState} from 'react';
import MenuItem from "react-bootstrap/lib/MenuItem";
import axios from "axios";
import DropdownButton from "react-bootstrap/lib/DropdownButton";
import _JSXStyle from 'styled-jsx/style'
import Button from "@material-ui/core/Button";
import ButtonGroup from "@material-ui/core/ButtonGroup";

export default function TopBar() {
    const [authOk, setAuthOk] = useState(false);
    let barRedirect = (endpoint) => console.log("barRedirect: " + endpoint);


    const checkAuthorization = (authOk) => {
        if (!authOk) {
            axios.get("http://localhost:8080/api/issfauthorized").then(response => {
                setAuthOk(response.data);
            });
            console.log("checked auth and got: " + authOk)
        }
    };

    useEffect(() => {
            console.log("Checked Auth in Did Mount");
            checkAuthorization();
        }
    );

    const logout = () => {
        setAuthOk(false);
    };
    
    return (
        <div align='center'>
            <div className="jsx-button">
                <_JSXStyle id="button">{`DropdownButton.jsx-123 {color: background: #117a8b;}`}</_JSXStyle>
                <ButtonGroup size="large" color="primary" aria-label="large outlined primary button group">
                    <Button
                        color="transparent"
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
                        color="transparent"
                        size="small">
                        <DropdownButton bsStyle='light' title='Settings'>
                            {!authOk
                                ? <MenuItem href='/login'>Login</MenuItem>
                                : null}
                            <MenuItem href='/calendar'>Calendar</MenuItem>
                            <MenuItem divider/>
                            {authOk
                                ? <MenuItem href="http://localhost:8080/logout" onClick={logout}>Logout</MenuItem>
                                : null
                            }
                        </DropdownButton>
                    </Button>
                    <Button
                        color="transparent"
                        size="small">
                        {authOk ?
                            <DropdownButton bsStyle='light' title='Services'>
                                <MenuItem href='/plot'>Plots</MenuItem>
                                <MenuItem href='/test'>Tests</MenuItem>
                                <MenuItem href='/analytics'>Tables</MenuItem>
                            </DropdownButton>
                            : null}
                    </Button>
                </ButtonGroup>
            </div>
        </div>
    );

}