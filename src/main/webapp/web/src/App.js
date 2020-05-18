import React, {Component} from 'react'

import './main/App.css'
import {BrowserRouter as Router, Switch, Route, Redirect} from 'react-router-dom'
import LoginForm from "./login/LoginForm"
import Members from "./topBar/about/Members"
import AnalyticsRouter from "./analytics/AnalyticsRouter"
import {TestComponent} from "./main/TestComponent";
import AboutTemplate from "./topBar/about/AboutTemplate";
import TopBar from "./topBar/TopBar";
import {connect} from "react-redux";

class App extends Component {



    render() {
        console.log("Host URL" + process.env.PUBLIC_URL)
        return (
            <div>
                <div>
                    <TopBar/>
                </div>
                <div>
                    <Router basename={process.env.PUBLIC_URL}>
                        <div className="App">

                            <Switch>
                                <Route exact path="/" render={() => (
                                    <Redirect to="/motivation"/>
                                )}/>
                                <Route exact path='/login' component={LoginForm}/>
                                <Route exact path='/test' component={TestComponent}/>
                                <Route exact path='/members' component={Members}/>
                                <Route exact path='/motivation' component={AboutTemplate}/>
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

const mapStateToProps = state => {
    return {
        authOk: state.authOk
    }
}

const mapDispatchToProps = dispatch => {
    return {
        onVerifyLogin: () => dispatch({type: 'VERIFY_AUTH'}),
        logout: () => dispatch({type: 'LOGOUT'})
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(App);