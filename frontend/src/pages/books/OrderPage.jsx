import { useGetOrderByEmailQuery } from "../../redux/features/orders/ordersApi";
import { useAuth } from "../../context/AuthContext";

const OrderPage = () => {
  const { currentUser } = useAuth();

  const {
    data: orders = [],
    isLoading,
    isError,
  } = useGetOrderByEmailQuery(currentUser.email);
  if (isLoading) return <div>Loading...</div>;
  if (isError) return <div>Error geting orders data</div>;
  return (
    <div className="container mx-auto p-6">
      <h2 className="text-2xl font-semibold mb-4">Your Orders</h2>
      {orders.length === 0 ? (
        <div>No orders found!</div>
      ) : (
        <div>
          {orders.map((order, index) => {
            const address = order.address || {}; // Default to an empty object
            return (
              <div key={order._id || index} className="border-b mb-4 pb-4">
                <p className="p-1 bg-secondary text-white w-10 rounded mb-1">
                  # {index + 1}
                </p>
                <h2 className="font-bold">Order ID: {order._id}</h2>
                <p className="text-gray-600">Name: {order.name}</p>
                <p className="text-gray-600">Email: {order.email}</p>
                <p className="text-gray-600">Phone: {order.phone}</p>
                <p className="text-gray-600">
                  Total Price: ${order.totalPrice}
                </p>
                <h3 className="font-semibold mt-2">Address:</h3>
                <p>
                  {address.city || "N/A"}, {address.state || "N/A"},{" "}
                  {address.country || "N/A"}, {address.zipcode || "N/A"}
                </p>
                <h3 className="font-semibold mt-2">Products Id:</h3>
                <ul>
                  {order.productIds?.map((productId, idx) => (
                    <li key={productId || idx}>{productId}</li>
                  )) || <li>No products listed</li>}
                </ul>
              </div>
            );
          })}
        </div>
      )}
    </div>
  );
};

export default OrderPage;
