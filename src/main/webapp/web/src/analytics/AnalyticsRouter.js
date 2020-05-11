import React, {Component, useEffect, useState} from 'react';

import TableComponent from "./TableComponent";
import ButtonGroup from "@material-ui/core/ButtonGroup";
import Button from "@material-ui/core/Button";
import EuroIcon from "@material-ui/icons/Euro";
import CameraIcon from "@material-ui/icons/Camera";
import SaveIcon from "@material-ui/icons/Save";
import {makeStyles} from "@material-ui/core/styles";
import axios from "axios";
import TopBar from "../topBar/TopBar";
import ChartsComponent from "./ChartsComponent";

const useStyles = makeStyles((theme) => ({
    root: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        '& > *': {
            margin: theme.spacing(1),
        },
    },
}));

export default function AnalyticsRouter(props) {
    let [endpoint, setEndpoint] = useState("opp");
    let [data, setData] = useState(props);
    const classes = useStyles();

    // useEffect( () => setData(this.props.data));
    // const submitToAzure = () => {
    //     axios({
    //         method: 'post',
    //         url: 'http://localhost:3000/',
    //         data: data
    //     })
    // };

    let forex = () => {
        console.log("Forex in custom router");
        setEndpoint("forex")
    };

    let opps = () => {
        setEndpoint("opps");
    };

    let tableComponent = <TableComponent/>;
    if (endpoint === "forex") {
        tableComponent = <ChartsComponent/>
    } else if (endpoint === "opps")
        tableComponent = <TableComponent/>;
    else tableComponent = <TableComponent/>;

    return (
        <div>
            <div className={classes.root}>
                <ButtonGroup size="large" color="primary" aria-label="large outlined primary button group">
                    <Button
                        color="transparent"
                        startIcon={<EuroIcon/>}
                        size="small"
                        onClick={forex}>Forex Data</Button>
                    <Button
                        color="transparent"
                        startIcon={<CameraIcon/>}
                        size="small"
                        onClick={opps}>Sf Opps</Button>
                    <Button
                        color="transparent"
                        size="small"
                        startIcon={<SaveIcon/>}>Save</Button>
                </ButtonGroup>
            </div>
            {tableComponent}
        </div>
    );
}
