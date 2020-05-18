import React, {useEffect, useState} from 'react';
import axios from 'axios'

export const TestComponent = () => {
    let [uid, setUid] = useState("react test");
    useEffect(() => {
        if (!uid) {
            setUid("Error loading Id");
        }
        axios.get("http://localhost:8080/api/uid").then(response => {
            console.log(response.data)
            setUid(response.data);
        });
    })

    return (
        <div>
            User ID: {uid}
        </div>
    );
}