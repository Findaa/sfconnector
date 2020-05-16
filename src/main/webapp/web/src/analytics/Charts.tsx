import {useEffect, useState} from 'react'
import * as React from 'react';
// @ts-ignore
import {LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend,}  from 'recharts';
import {Calendar} from "../calendar/Calendar";

export const Charts: React.FunctionComponent = (props: any) => {
    let [chart, setChart] = useState([])
    let loadForex = () => {
        fetch('http://localhost:8080/api/forex')
            .then(response => response.json())
            .then(data => {
                // delete data['attributes'];
                console.log("data")
                console.log(data)
                data.sort((a: any, b: any) => a.date.localeCompare(b.date));
                setChart(data)
            })
            .catch((err) => {
                console.error(props.url, err.toString())
                console.log("no printerino")
            })
    };

    useEffect(() => {
        if (chart.length < 1) loadForex()

    })

    return (
        <div style={{display: 'flex',  justifyContent:'center', alignItems:'center', height: '100vh'}}>
            <Calendar func={loadForex}/>
            <LineChart
                width={900}
                height={600}
                data={chart}
                margin={{top: 5, right: 30, left: 20, bottom: 5,}}>
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="date" />
                <YAxis />
                <Tooltip />
                <Legend />
                <Line type="monotone" dataKey="quotes.USDAUD" stroke="#8884d8" name="USD-AUD"/>
                <Line type="monotone" dataKey="quotes.USDCAD" stroke="#82ca9d" name="USDCAD"/>
                {/*<Line type="monotone" dataKey="quotes.USDMXN" stroke="#82ca9d" name="USDMXN"/>*/}
                <Line type="monotone" dataKey="quotes.USDPLN" stroke="#82ca9d" name="USDPLN"/>
            </LineChart>
        </div>

    );
}
