import React, {Component, useEffect} from 'react';
import Tile from "../../tile/Tile";


export default function AboutTemplate() {
    let [tileId, setTitleId] = React.useState(3);


    useEffect(() => {
            let parts: string[] = window.location.href.split('/');
            let definition: any = parts.pop() || parts.pop();

            if (definition.toString() === "motivation") setTitleId(4);
            else if (definition.toString() === 'flow') setTitleId(5);
            else if (definition.toString() === 'res') setTitleId(6);
            else setTitleId(3);

            console.log(definition);
        }
    );

    return (
        <div>
            <Tile val={tileId}/>
        </div>
    );
}