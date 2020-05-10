import ReactTable, {useTable, useSortBy} from "react-table";
import {useEffect, useState} from "react";
import * as React from "react";
import CssBaseline from '@material-ui/core/CssBaseline'
import axios from 'axios';
import styled from 'styled-components'
import Button from "@material-ui/core/Button";
import SaveIcon from '@material-ui/icons/Save';
import MaUTable from '@material-ui/core/Table'
import TableBody from '@material-ui/core/TableBody'
import TableCell from '@material-ui/core/TableCell'
import TableHead from '@material-ui/core/TableHead'
import TableRow from '@material-ui/core/TableRow'

export default function TableComponent() {
    const [data, setData] = useState([]);
    useEffect(() => {
        if (data.length < 10) loadData()
    });

    function loadData() {
        fetch('http://localhost:8080/api/opportunities')
            .then(response => response.json())
            .then(data => {
                delete data['attributes'];
                console.log("data");
                console.log(data);
                setData(data);

            })
            .catch((err) => {
                console.error(this.props.url, err.toString());
                console.log("no printerino")
            })
    }

    const submitToAzure = () => {
        axios({
            method: 'post',
            url: 'http://localhost:8080/api/overwrite',
            data: data
        })
    };

    const Styles = styled.div`
  padding: 1rem;
  table {
    border-spacing: 0;
    border: 1px solid black;
    tr {
      :last-child {
        td {
          border-bottom: 0;
        }
      }
    }
    th,
    td {
      margin: 0;
      padding: 0.5rem;
      border-bottom: 1px solid black;
      border-right: 1px solid black;
      :last-child {
        border-right: 0;
      }
    }
  }
`;
    function Table({ columns, data }) {
        const {
            getTableProps,
            getTableBodyProps,
            headerGroups,
            rows,
            prepareRow,
        } = useTable(
            {
                columns,
                data,
            },
            useSortBy
        )

        // We don't want to render all 2000 rows for this example, so cap
        // it at 20 for this use case
        const firstPageRows = rows.slice(0, 20)

        return (
            <>
                <table {...getTableProps()}>
                    <thead>
                    {headerGroups.map(headerGroup => (
                        <tr {...headerGroup.getHeaderGroupProps()}>
                            {headerGroup.headers.map(column => (
                                // Add the sorting props to control sorting. For this example
                                // we can add them into the header props
                                <th {...column.getHeaderProps(column.getSortByToggleProps())}>
                                    {column.render('Header')}
                                    {/* Add a sort direction indicator */}
                                    <span>
                    {column.isSorted
                        ? column.isSortedDesc
                            ? ' 🔽'
                            : ' 🔼'
                        : ''}
                  </span>
                                </th>
                            ))}
                        </tr>
                    ))}
                    </thead>
                    <tbody {...getTableBodyProps()}>
                    {firstPageRows.map(
                        (row, i) => {
                            prepareRow(row);
                            return (
                                <tr {...row.getRowProps()}>
                                    {row.cells.map(cell => {
                                        return (
                                            <td {...cell.getCellProps()}>{cell.render('Cell')}</td>
                                        )
                                    })}
                                </tr>
                            )}
                    )}
                    </tbody>
                </table>
                <br />
                <div>Showing the first 20 results of {rows.length} rows</div>
            </>
        )
    }

    const columns = React.useMemo(
        () => [
            {
                Header: 'Opportunities',
                showPaginationTop: true,
                showPaginationBottom: false,
                showPageSizeOption: false,
                columns: [
                    {
                        Header: 'ID',
                        accessor: 'Id',
                        maxWidth: 100,
                        minWidth: 100
                    },
                    {
                        Header: 'Name',
                        accessor: 'Name',
                        maxWidth: 100,
                        minWidth: 100
                    },
                    {
                        Header: 'Amount (value)',
                        accessor: 'Amount',
                        maxWidth: 100,
                        minWidth: 100
                    },
                    {
                        Header: 'Stage',
                        accessor: 'StageName',
                        maxWidth: 100,
                        minWidth: 100
                    },
                ],
            },
        ],
        []
    );
    return (
        <div>
            <Button
                variant="contained"
                color="primary"
                size="small"
                startIcon={<SaveIcon/>}
                onClick={submitToAzure}
            >
                Save
            </Button>
            <Styles>
                <Table columns={columns} data={data} />
            </Styles>
        </div>
    )

}