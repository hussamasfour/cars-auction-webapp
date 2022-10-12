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
const carService = {
  searchCar,
  getAllCars,
  getCarById,
};

export default carService;
