import React, {Component} from "react";
import 'react-calendar/dist/Calendar.css';
import TimePicker from "./TimePicker";

export default class Calendar extends Component {
    constructor(props) {
        super(props);
        this.state = {
            date: new Date(),
            enableClock: false,
        };
    }

    onChange(date) {
        this.setState({date: date});
        console.log("date")
    }

    onClick(value) {
        this.setState({date: value});
        console.log("date")
    }

    showClock() {
        this.setState({enableClock: !this.state.enableClock});
    };

    render() {
        return (
            <div>
                <TimePicker/>
            </div>
        )
    }
}