import React, {Component} from 'react';
import Tile from "../../tile/Tile";


export default class AboutTemplate extends Component {
    state = {
        tileId: 3
    };

    componentDidMount() {
        let parts = window.location.href.split('/');
        let definition = parts.pop() || parts.pop();

        if (definition.toString() === "motivation") this.setState({tileId: 4});
        else if (definition.toString() === 'flow') this.setState({tileId: 5});
        else if (definition.toString() ==='res') this.setState({tileId: 6});
        else this.setState({tileId: 3});

        console.log(this.state.tileId + ": number");
        console.log(definition);
    }


    render() {
        return (
            <div>
                <Tile val={this.state.tileId}/>
            </div>
        );
    }
}