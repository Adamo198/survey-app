module.exports = {
  devServer: {
    proxy: {
      '^/api': {
        target: 'http://api-gateway:9090',
        changeOrigin: true,
        ws:true,
        logLevel: 'debug',
        pathRewrite: { '^/api': '/api/' },
      },
    },
  },
}
