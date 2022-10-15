import ApiConnecter from "../api/apiConnecter";

const searchCar = async (data) => {
  const response = await ApiConnecter.get("/search-car", {
    params: {
      query: data,
    },
  });
  return response;
};

const getAllCars = async () => {
  return await ApiConnecter.get("/car");
};

const getCarById = async (id) => {
  return await ApiConnecter.get("/car/" + id);
};

const placeBid = async (car_id, bidAmount) => {
  return await ApiConnecter.post("/bid", {
    params: {
      car_id: car_id,
      amount: bidAmount,
    },
  });
};
const carService = {
  searchCar,
  getAllCars,
  getCarById,
  placeBid,
};

export default carService;
