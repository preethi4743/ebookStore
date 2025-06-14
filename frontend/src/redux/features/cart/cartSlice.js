import { createSlice } from "@reduxjs/toolkit";
import Swal from "sweetalert2";

const initialState = {
  cartItems: [],
};

const cartSlice = createSlice({
  name: "cart",
  initialState,
  reducers: {
    addToCart: (state, action) => {
      const id = action.payload._id || action.payload.id;

      if (!id) {
        console.warn(
          "❌ Cannot add to cart: Missing _id or id in payload",
          action.payload
        );
        Swal.fire({
          icon: "error",
          title: "Missing Product ID",
          text: "Something went wrong while adding this product.",
        });
        return;
      }

      const existingItem = state.cartItems.find((item) => item._id === id);
      if (!existingItem) {
        state.cartItems.push({ ...action.payload, _id: id }); // Ensure _id is set
        Swal.fire({
          position: "top-end",
          icon: "success",
          title: "Product Added to the Cart",
          showConfirmButton: false,
          timer: 1500,
        });
      } else {
        Swal.fire({
          title: "Already Added to the Cart",
          text: "This item is already in your cart.",
          icon: "warning",
          confirmButtonColor: "#3085d6",
          confirmButtonText: "OK",
        });
      }
    },

    removeFromCart: (state, action) => {
      const id = action.payload._id || action.payload.id;
      state.cartItems = state.cartItems.filter((item) => item._id !== id);
    },

    clearCart: (state) => {
      state.cartItems = [];
    },
  },
});

export const { addToCart, removeFromCart, clearCart } = cartSlice.actions;
export default cartSlice.reducer;
