import React, {useState, useEffect} from 'react';
import './TopBarButton.css';

export default function TopBarButton(props) {
    const [action, setAction] = useState(function (s) {
        console.log(s)
    });

    return (
        <button type="button" onClick={action}>
            {props.name}
        </button>
    )
}

