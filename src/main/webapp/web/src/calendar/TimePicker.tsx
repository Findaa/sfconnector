import React, {useState} from 'react';
// @ts-ignore
import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";
import 'bootstrap/dist/css/bootstrap.min.css';

export default function TimePicker(props: any) {
    const [startDate, setStartDate] = useState(new Date());
    let [data, setData] = useState([])

    let submitForexSearch = () => {
        console.log("submitForexSearch");
        console.log(startDate)
        let string = startDate.toString().substring(4, 15)
        console.log("String: " + string)
        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({date: string})
        }

        fetch('http://localhost:8080/api/searchforexdate', requestOptions)
            .then(response => response.json())
            .then(data => {
                setData(data)

            })
        console.log(data)
        props.func()

    }


    return (
            <div className="form-group">
                <DatePicker
                    selected={startDate}
                    onChange={(date: any) => {
                        setStartDate(date)
                    }
                    }
                    inline
                />
                <button className="btn btn-primary" onClick={submitForexSearch}>Show Date</button>
                {data ? data : ''}
            </div>
    )
}
