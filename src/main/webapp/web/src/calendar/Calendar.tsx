import 'react-calendar/dist/Calendar.css';
import * as React from 'react'
import TimePicker from "./TimePicker";

export const Calendar = (props: any) => {

    return (
        <div>
            <TimePicker func={props.func}/>
        </div>
    )
}