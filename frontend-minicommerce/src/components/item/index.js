import React, {useState} from "react";
import Button from "../button";
import classes from "./styles.module.css";
import APIConfig from "../../api/APIConfig";

const Item = (props) => {
    const [totalAdded, setTotalAdded] = useState("");
    const { id, title, price, description, category, quantity, handleEdit, handleDelete, addToCartList, type="item", totalQuantity} = props;

    const handleChangeCart = (e)=>{
        const reg = new RegExp('^[0-9]+$');

        const {value} = e.target;
        if(reg.test(value)){
            setTotalAdded(value);
        }
        else if(value===""){
            setTotalAdded("")
        }

    }
    const handleAddToCart = async()=>{
        try {
            if(quantity - parseInt(totalAdded)<0){
                alert("stok tidak memenuhi")
            }
            else{

                const payload = {
                    idItem:id,
                    quantity:parseInt(totalAdded)
                }

                const { data } = await APIConfig.post(`/cart`,payload);
                await addToCartList();
                setTotalAdded("")
            }

        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }

    }
    return (
        <div className={classes.item}>
            <h3>{`ID ${id}`}</h3>
            <p>{`Nama Barang: ${title}`}</p>
            <p>{`Harga: ${price}`}</p>
            {type==="cart" && <p>{`Jumlah: ${totalQuantity}`}</p>}
            <p>{`Deskripsi: ${description}`}</p>
            <p>{`Kategori: ${category}`}</p>
            {
                type==="item" &&
                <>
                    <p>{`Stok: ${quantity}`}</p>
                    <Button action={handleEdit}>
                        Edit
                    </Button>
                    <Button action={handleDelete}>
                        Delete
                    </Button>
                    <div>
                        <input onChange={handleChangeCart} value={totalAdded}/>
                        <Button action={handleAddToCart} disabled={totalAdded.length===0}>
                            Add to Cart
                        </Button>
                    </div>

                </>
            }
            {type==="cart" && <b>{`Total Harga: ${totalQuantity * price}`}</b>}


        </div>

    );
};

export default Item;