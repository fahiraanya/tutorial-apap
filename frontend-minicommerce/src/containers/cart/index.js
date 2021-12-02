import React, { Component } from "react";
import {Link} from 'react-router-dom';
import Item from "../../components/item";
import APIConfig from "../../api/APIConfig";
import classes from "./styles.module.css";

class Cart extends Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [],
            isLoading: false,
            id: "",
            title: "",
            price: 0,
            description: "",
            category: "",
            quantity: 0
        };

        this.handleCheckoutItem = this.handleCheckoutItem.bind(this);
        this.loadData = this.loadData.bind(this);
    }
    handleEditItem(item) {
        this.setState({
            isEdit: true,
            id: item.id,
            title: item.title,
            price: item.price,
            description: item.description,
            category: item.category,
            quantity: item.quantity
        })
    }

    componentDidMount() {
        console.log("componentDidMount()");
        this.loadData();
    }

    async loadData() {
        try {
            const { data } = await APIConfig.get("/cart");
            this.setState({ items: data.result });
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }


    shouldComponentUpdate(nextProps, nextState) {
        console.log("shouldComponentUpdate()");
        return true;
    }

    async handleCheckoutItem() {
        try {
            await APIConfig.get(`/cart/checkout`);
            this.setState({ items: [] });
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }

    render() {
        console.log(this.state.items, "items")
        return (
            <div className={classes.itemList}>
                <h1 className={classes.title}>Cart Items</h1>
                <div style={ {position: "fixed", top: 25, left: 25} } >
                    <Link to="/">

                        <button>Back</button>
                    </Link>

                </div>
                {
                    this.state.items.length>0 &&
                    <div style={ {position: "fixed", top: 25, right: 25} }>
                        <button onClick={this.handleCheckoutItem}>Checkout</button>
                    </div>
                }
                <div>
                    {this.state.items.map((element) => (
                        <Item
                            key={element.id}
                            id={element.id}
                            title={element.item.title}
                            totalQuantity={element.quantity}
                            price={element.item.price}
                            description={element.item.description}
                            category={element.item.category}
                            quantity={element.item.quantity}
                            type={"cart"}

                        />
                    ))}
                </div>
            </div>
        );
    }
}

export default Cart;