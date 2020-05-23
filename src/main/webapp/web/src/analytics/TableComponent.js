import ReactTable, {useTable, useSortBy} from "react-table";
import {useEffect, useState} from "react";
import * as React from "react";
import styled from 'styled-components'
import {Link} from "react-router-dom";
import {connect} from 'react-redux'
import {makeStyles} from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';


const useStyles = makeStyles({
    table: {
        minWidth: 650,
    },
});

function TableComponent(props) {
    const [data, setData] = useState([]);
    useEffect(() => {
        if (data.length < 10) loadDataOpps()

    });

    function loadDataOpps() {
        fetch('http://localhost:8080/api/opportunities')
            .then(response => response.json())
            .then(data => {
                // delete data['attributes'];
                console.log("data");
                console.log(data);
                setData(data);
                props.saveDataToRedux(data)
            })
            .catch((err) => {
                console.error(props.url, err.toString());
                console.log("no printerino")
            })
    }

    const classes = useStyles();

    let generateUrl = (link) => {
        let id = link.slice(-18);
        let redirectUrl = 'https://mcopue-dev-ed.lightning.force.com/lightning/r/Opportunity/' + id + '/view'
        return <a href={redirectUrl}>Go to salesforce {id}</a>
    }



    return (
        <TableContainer component={Paper}>
            <Table className={classes.table} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell>Id</TableCell>
                        <TableCell align="right">Name</TableCell>
                        <TableCell align="right">Stage Name</TableCell>
                        <TableCell align="right">Amount</TableCell>
                        <TableCell align="right">Close Date</TableCell>
                        <TableCell align="right">Salesforce Link</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {props.data.map((row) => (
                        <TableRow key={row.id} onClick={() => console.log("clicked")}>
                            <TableCell component="th" scope="row">
                                {row.Id}
                            </TableCell>
                            <TableCell align="right">{row.Name}</TableCell>
                            <TableCell align="right">{row.StageName}</TableCell>
                            <TableCell align="right">{row.Amount}</TableCell>
                            <TableCell align="right">{row.CloseDate}</TableCell>

                            <TableCell
                                align="right">{generateUrl(row.attributes ? row.attributes.url : 'elo')}</TableCell>

                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    )
}

const mapStateToProps = state => {
    return {
        data: state.data
    }
}

const mapDispatchToProps = dispatch => {
    return {
        saveDataToRedux: data => {
            dispatch({type: 'SAVE_DATA', data: data})
        }
    }
}


export default connect(mapStateToProps, mapDispatchToProps)(TableComponent)