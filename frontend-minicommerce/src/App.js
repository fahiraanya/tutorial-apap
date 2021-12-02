import React, { Component } from "react";
import Layout from "./components/layout";
import {BrowserRouter as Routes, Switch, Route} from "react-router-dom";
import ItemList from "./containers/itemlist";
import Cart from "./containers/cart";
class App extends Component {
    render() {
        return (
            <Routes>
                <Switch>
                    <Route exact path="/">
                        <Layout>
                            <ItemList/>
                        </Layout>
                    </Route>
                    <Route exact path="/cart">
                        <Layout>
                            <Cart/>
                        </Layout>
                    </Route>
                </Switch>
            </Routes>

        );
    }
}
export default App;