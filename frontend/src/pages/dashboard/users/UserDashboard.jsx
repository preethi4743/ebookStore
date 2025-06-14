import { useAuth } from "../../../context/AuthContext";
import { useGetOrderByEmailQuery } from "../../../redux/features/orders/ordersApi";

const UserDashboard = () => {
  const { currentUser } = useAuth();
  const {
    data: orders = [],
    isLoading,
    isError,
  } = useGetOrderByEmailQuery(currentUser?.email);

  console.log("Orders:", orders); // Debug output

  if (isLoading) return <div>Loading...</div>;
  if (isError) return <div>Error getting orders data</div>;

  return (
    <div className="bg-gray-100 py-16">
      <div className="max-w-4xl mx-auto bg-white shadow-md rounded-lg p-6">
        <h1 className="text-2xl font-bold mb-4">User Dashboard</h1>
        <p className="text-gray-700 mb-6">
          Welcome, {currentUser?.username || "User"}! Here are your recent
          orders:
        </p>

        <div className="mt-6">
          <h2 className="text-xl font-semibold mb-4">Your Orders</h2>
          {orders.length > 0 ? (
            <ul className="space-y-4">
              {orders.map((order, index) => (
                <li
                  key={order.id || index}
                  className="bg-gray-50 p-4 rounded-lg shadow-sm space-y-1"
                >
                  <p className="font-medium">Order ID: {order.id || "N/A"}</p>
                  <p>
                    Date:{" "}
                    {order.createdAt
                      ? new Date(order.createdAt).toLocaleDateString()
                      : "N/A"}
                  </p>
                  <p>
                    Total: $
                    {order.totalPrice ? order.totalPrice.toFixed(2) : "0.00"}
                  </p>
                  <div>
                    <p className="font-semibold">Products Ids:</p>
                    {order.productIds && order.productIds.length > 0 ? (
                      order.productIds.map((productId, i) => (
                        <p
                          key={`${order.id || index}-product-${i}`}
                          className="ml-1 text-sm text-gray-600"
                        >
                          {productId}
                        </p>
                      ))
                    ) : (
                      <p className="text-sm text-gray-500">No products found</p>
                    )}
                  </div>
                </li>
              ))}
            </ul>
          ) : (
            <p className="text-gray-600">You have no recent orders.</p>
          )}
        </div>
      </div>
    </div>
  );
};

export default UserDashboard;
