import {Component, useState} from "react";
import 'react-calendar/dist/Calendar.css';
import * as React from 'react'
import TimePicker from "./TimePicker";

export const Calendar = () => {

    let [date, setDate] = useState(new Date())
    let [enableClock, setEnableClock] = useState(false)

    let onChange = (date: any) => {
        setDate(date)
    }

    let onClick = (value: any) => {
        setDate(value)
        console.log("date")
    }
    let showClock = () => {
        setEnableClock(!enableClock)
    };

    return (
        <div>
            <TimePicker/>
        </div>
    )
}