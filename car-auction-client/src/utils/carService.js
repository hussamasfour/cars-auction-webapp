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

const carService = {
  searchCar,
  getAllCars,
};

export default carService;
