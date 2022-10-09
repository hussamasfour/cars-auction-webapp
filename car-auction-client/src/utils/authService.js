import ApiConnecter from "../api/apiConnecter";

const register = async (data) => {
  return await ApiConnecter.post("/signup", data);
};

const login = async (data) => {
  return await ApiConnecter.post("/login", data).then((response) => {
    localStorage.setItem("user", JSON.stringify(response.data));
  });
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};
const AuthService = {
  register,
  login,
  getCurrentUser,
};

export default AuthService;
