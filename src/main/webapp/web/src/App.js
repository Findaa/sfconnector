import React, {Component} from 'react';

import './App.css';
import Projects from './main/Projects'
import {BrowserRouter as Router, Switch, Route, Redirect} from 'react-router-dom';
import TopBar from "./topBar/TopBar";
import TestComponent from "./main/TestComponent";
import LoginForm from "./login/LoginForm";
import Calendar from "./calendar/Calendar";

class App extends Component {

    render() {
        console.log("Host URL" + process.env.PUBLIC_URL);
        return (
            <div>
                <div>
                    <TopBar />
                </div>
                <div>
                    <Router basename={process.env.PUBLIC_URL}>
                        <div className="App">
                            <header className="App-header">
                                <h1 className="App-title">Salesforce Statistics</h1>
                            </header>
                            <Switch>
                                <Route exact path="/" render={() => (
                                    <Redirect to="/projects"/>
                                )}/>
                                <Route exact path='/projects' component={Projects}/>
                                <Route exact path='/members' component={TestComponent}/>
                                <Route exact path='/login' component={LoginForm}/>
                                <Route exact path='/calendar' component={Calendar}/>
                            </Switch>
                        </div>
                    </Router>
                </div>
            </div>
        );
    }
}

export default App;
