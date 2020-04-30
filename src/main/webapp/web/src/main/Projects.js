import React, {Component} from 'react';
import Panel from 'react-bootstrap/lib/Panel'
import Button from 'react-bootstrap/lib/Button'
import ProjectDetails from './ProjectDetails'
import axios from 'axios'

export default class Projects extends Component {

    constructor(props) {
        super(props);
        this.state = {
            selectedProject: 1
        }
    }

    componentDidMount() {
        this.getProjectData();
    }

    getProjectData() {
        axios.get('assets/samplejson/projectlist.json').then(response => {
            this.setState({projectList: response})
        })
    };

    render() {
        if (!this.state.projectList)
            return (<p>Loading data</p>);
        return (<div className="addmargin">
            <div className="col-md-3"> {
                this.state.projectList.data.map(project =>
                    <Panel bsStyle="info" key={project.name} className="centeralign">
                        <Panel.Heading>
                            <Panel.Title componentClass="h3">{project.name}</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>
                            <p>{project.descr}</p>
                            <Button bsStyle="info" onClick={() => this.setState({selectedProject: project.id})}>
                                Click to View Details
                            </Button>
                        </Panel.Body>
                    </Panel>
                )
            }
            </div>
            <div className="col-md-6">
                <ProjectDetails val={this.state.selectedProject}/>
            </div>
        </div>)
    }

}
