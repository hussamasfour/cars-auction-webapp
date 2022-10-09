import ApiConnecter from "../api/apiConnecter";

const register = async (data) => {
  return await ApiConnecter.post("/signup", data);
};

const login = async (data) => {
  return await ApiConnecter.post("/login", data).then((response) => {
    localStorage.setItem("user", JSON.stringify(response.data));
  });
};

const AuthService = {
  register,
  login,
};

export default AuthService;
