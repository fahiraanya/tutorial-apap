import React, { Component } from "react";
import Item from "../../components/item";
import classes from "./styles.module.css";
import APIConfig from "../../api/APIConfig";
import Button from "../../components/button";
import Modal from "../../components/modal";
import { Fab } from "@material-ui/core";
import Badge from "@material-ui/core/Badge";
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import {Link} from "react-router-dom"

class ItemList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [],
            cartItems: [],
            cartHidden: true,
            isLoading: false,
            isCreate: false,
            isEdit: false,
            isSearch: false,
            id: "",
            title: "",
            price: 0,
            description: "",
            category: "",
            quantity: 0
        };
        this.handleClickLoading = this.handleClickLoading.bind(this);
        this.handleAddItem = this.handleAddItem.bind(this);
        this.handleCancel = this.handleCancel.bind(this);
        this.handleChangeField= this.handleChangeField.bind(this);
        this.handleSubmitItem = this.handleSubmitItem.bind(this);
        this.handleEditItem = this.handleEditItem.bind(this);
        this.handleSubmitEditItem = this.handleSubmitEditItem.bind(this);
        this.handleSearchItem = this.handleSearchItem.bind(this);
        this.handleSubmitSearch = this.handleSubmitSearch.bind(this);
        this.handleToggle = this.handleToggle.bind(this);
        this.getCart = this.getCart.bind(this);
    }
    componentDidMount() {
        this.loadData();
        console.log("componentDidMount()");
    }
    async loadData() {
        try {
            const { data } = await APIConfig.get("/item");
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
    handleToggle = () => {
        const cartHidden = this.state.cartHidden;
        this.setState({ cartHidden: !cartHidden});
    };
    handleClickLoading() {
        const currentLoading = this.state.isLoading;
        this.setState({ isLoading: !currentLoading });
        console.log(this.state.isLoading);
    }
    handleAddItem() {
        this.setState({ isCreate:true });
    }
    handleSearchItem() {
        this.setState({ isSearch:true });
        this.setState({ items:[] });
    }
    handleCancel(event) {
        event.preventDefault();
        this.setState({ isCreate:false, isEdit: false, isSearch:false });
        this.loadData();
    }
    handleChangeField(event) {
        const { name, value } = event.target;
        this.setState({ [name]: value });
    }
    async handleSubmitItem(event) {
        event.preventDefault();
        try {
            const data = {
                title: this.state.title,
                price: this.state.price,
                description: this.state.description,
                category: this.state.category,
                quantity: this.state.quantity
            };
            await APIConfig.post("/item", data);
            this.setState({
                title: "",
                price: 0,
                description: "",
                category: "",
                quantity: 0
            })
            this.loadData();
            console.log("add");
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }

        // this.handleCancel(event);
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
    async handleSubmitEditItem(event) {
        event.preventDefault();
        try {
            const data = {
                title: this.state.title,
                price: this.state.price,
                description: this.state.description,
                category: this.state.category,
                quantity: this.state.quantity
            };
            await APIConfig.put(`/item/${this.state.id}`, data);
            this.setState({
                id: "",
                title: "",
                price: 0,
                description: "",
                category: "",
                quantity: 0
            })
            this.loadData();
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
        // this.handleCancel(event);
    }
    async handleSubmitSearch(event) {
        event.preventDefault();
        try{
            const title = this.state.title;
            const { data } = await APIConfig("/item?title=" + title);
            this.setState({ items:data.result })
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }
    async getCart() {
        try {
            const { data } = await APIConfig.get("/cart");
            this.setState({ cartItems: data.result });
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }
    render() {
        return (
            <div className={classes.itemList}>
                <h1 className={classes.title}>
                    All Items
                </h1>
                <div style={ {position: "fixed", top: 25, right: 25} }>
                    <Link to="/cart">
                        <Fab variant="extended">
                            <Badge color="secondary" badgeContent={this.state.cartItems.length}>
                                <ShoppingCartIcon />
                            </Badge>
                        </Fab>
                    </Link>
                </div>

                <div className="container pt-3">
                    <div className="row mt-3">
                        {!this.state.cartHidden ? (
                            <div className="col-sm">
                                <p>hello world</p>
                            </div>
                            ) :
                            <div className="col-sm">
                                <Button action={this.handleAddItem}>
                                    Add Item
                                </Button>
                                <Button action={this.handleSearchItem}>
                                    Search Item
                                </Button>
                                {this.state.items.map((item) => (
                                    <Item
                                        key={item.id}
                                        id={item.id}
                                        title={item.title}
                                        price={item.price}
                                        description={item.description}
                                        addToCartList={this.getCart}
                                        category={item.category}
                                        quantity={item.quantity}
                                        handleEdit = {() => (this.handleEditItem(item))}
                                    />
                                ))}
                            </div>
                        }
                    </div>
                </div>
                {/* search */}
                <Modal
                    show={this.state.isSearch}
                    handleCloseModal={this.handleCancel}
                    modalTitle={"Search item"}>
                    <form>
                        <input
                        className={classes.textField}
                        type="text"
                        placeholder="Nama Item"
                        name="title"
                        value={this.state.title}
                        onChange={this.handleChangeField}/>
                    </form>
                    <div>
                        {this.state.items.map((item) => (
                            <Item
                            key={item.id}
                            id={item.id}
                            title={item.title}
                            price={item.price}
                            description={item.description}
                            category={item.category}
                            quantity={item.quantity}/>
                        ))}
                    </div>
                    <Button action={this.handleSubmitSearch}>Search</Button>
                    <Button action={this.handleCancel}>Cancel</Button>
                </Modal>
                {/* add or edit */}
                <Modal
                    show={this.state.isCreate || this.state.isEdit}
                    handleCloseModal={this.handleCancel}
                    modalTitle={this.state.isCreate
                        ? "Add Item"
                        : `Edit Item ID ${this.state.id}`}
                >
                    <form>
                        <input
                            className={classes.textField}
                            type="text"
                            placeholder="Nama Item"
                            name="title"
                            value={this.state.title}
                            onChange={this.handleChangeField}
                        />
                        <input
                            className={classes.textField}
                            type="number"
                            placeholder="Harga"
                            name="price"
                            value={this.state.price}
                            onChange={this.handleChangeField}
                        />
                        <textarea
                            className={classes.textField}
                            placeholder="Deskripsi"
                            name="description"
                            rows="4"
                            value={this.state.description}
                            onChange={this.handleChangeField}
                        />
                        <input className={classes.textField}
                                        type="text"
                                        placeholder="Kategori"
                                        name="category"
                                        value={this.state.category}
                                        onChange={this.handleChangeField}
                        />
                        <input
                            className={classes.textField}
                            type="number"
                            placeholder="qty"
                            name="quantity"
                            value={this.state.quantity}
                            onChange={this.handleChangeField}
                        />
                        <Button action={this.state.isCreate
                            ? this.handleSubmitItem
                            : this.handleSubmitEditItem}
                        >
                            Create
                        </Button>
                        <Button action={this.handleCancel}>
                            Cancel
                        </Button>
                    </form>
                </Modal>
            </div>
        );
    }
}
export default ItemList;