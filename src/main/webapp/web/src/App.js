import React, {Component} from 'react'

import './main/App.css'
import Projects from './main/Projects'
import {BrowserRouter as Router, Switch, Route, Redirect} from 'react-router-dom'
import TopBar from "./topBar/TopBar"
import LoginForm from "./login/LoginForm"
import Members from "./topBar/about/Members"
import AnalyticsRouter from "./analytics/AnalyticsRouter"
import {Calendar} from "./calendar/Calendar";
import {TestComponent} from "./main/TestComponent";
import AboutTemplate from "./topBar/about/AboutTemplate";


export default class App extends Component {

    render() {
        console.log("Host URL" + process.env.PUBLIC_URL)
        return (
            <div>
                <div >
                    <TopBar/>
                </div>
                <div>
                    <Router basename={process.env.PUBLIC_URL}>
                        <div className="App">

                            <Switch>
                                <Route exact path="/" render={() => (
                                    <Redirect to="/projects"/>
                                )}/>

                                <Route exact path='/projects' component={Projects}/>
                                <Route exact path='/login' component={LoginForm}/>
                                <Route exact path='/calendar' component={Calendar}/>
                                <Route exact path='/test' component={TestComponent}/>
                                <Route exact path='/members' component={Members}/>
                                <Route exact path='/motivation' component={AboutTemplate}/>
                                <Route exact path='/res' component={AboutTemplate}/>
                                <Route exact path='/flow' component={AboutTemplate}/>
                                <Route exact path='/analytics' component={AnalyticsRouter}/>
                            </Switch>
                        </div>
                    </Router>
                </div>
            </div>
        );
    }
}
