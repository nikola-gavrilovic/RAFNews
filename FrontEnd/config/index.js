var proxyTable = {
  "/demo/api": {
    target: "http://localhost:8080",
    changeOrigin: true,
  },
};
