import {Component, useEffect, useState} from 'react'
import ButtonGroup from "@material-ui/core/ButtonGroup"
import Button from "@material-ui/core/Button"
import EuroIcon from "@material-ui/icons/Euro"
import CameraIcon from "@material-ui/icons/Camera"
import SaveIcon from "@material-ui/icons/Save"
import {makeStyles} from "@material-ui/core/styles"
import {Charts} from "./Charts"
import * as React from 'react'
import TableComponent from './TableComponent';
// @ts-ignore
import { CSVLink, CSVDownload } from "react-csv"

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

export default function AnalyticsRouter(props: any) {
    let [endpoint, setEndpoint] = useState("forex")


    const classes = useStyles()

    let forex = () => {
        console.log("Forex in custom router");
        setEndpoint("forex")
    }

    let opps = () => {
        setEndpoint("opps");
    }


    let tableComponent = <TableComponent/>
    if (endpoint === "forex") {
        tableComponent = <Charts/>
    } else if (endpoint === "opps")
        tableComponent = <TableComponent/>
    else tableComponent = <TableComponent/>

    return (
        <div>
            <div className={classes.root}>
                <ButtonGroup size="large" color="primary" aria-label="large outlined primary button group">
                    <Button
                        startIcon={<EuroIcon/>}
                        size="small"
                        onClick={forex}>Forex Data</Button>
                    <Button
                        startIcon={<CameraIcon/>}
                        size="small"
                        onClick={opps}>Sf Opps</Button>
                    <Button
                        size="small"
                        startIcon={<SaveIcon/>}
                        />
                        {/*Todo: Implement this button*/}
                    {/*><CSVLink data={}>Download me</CSVLink>;</Button>*/}
                </ButtonGroup>
            </div>
            {tableComponent}
        </div>
    )
}