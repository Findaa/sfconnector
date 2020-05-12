import React, {Component, useEffect, useState} from 'react';
import Tile from "../tile/Tile";
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
            <Tile val='1'/>
            <Tile val='2'/>

            User ID: {uid}

        </div>
    );
}