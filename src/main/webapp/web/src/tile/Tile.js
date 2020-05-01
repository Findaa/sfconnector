import React, {Component} from 'react';
import Panel from 'react-bootstrap/lib/Panel'
import axios from 'axios'

export default class Tile extends Component {
    constructor(props) {
        super(props);
        this.state = {}
    }

    componentDidMount() {
        this.getCustomerDetails(this.props.val);
    }

    componentDidUpdate(prevProps) {
        if (this.props.val !== prevProps.val) {
            this.getCustomerDetails(this.props.val);
        }
    }

    getCustomerDetails(id) {
        axios.get('assets/samplejson/project' + id + '.json').then(response => {
            this.setState({customerDetails: response})
        })
    };

    render() {
        if (!this.state.customerDetails)
            return (<p>Loading Data</p>);
        return (
            <div className="customerdetails">
                <Panel bsStyle="info" className="centeralign">
                    <Panel.Heading>
                        <Panel.Title componentClass="h3">{this.state.customerDetails.data.name}</Panel.Title>
                    </Panel.Heading>
                    <Panel.Body>
                        <p>{this.state.customerDetails.data.name}</p>
                        <p>{this.state.customerDetails.data.descr}</p>
                    </Panel.Body>
                </Panel>
            </div>)
    }
}
