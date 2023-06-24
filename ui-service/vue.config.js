module.exports = {
  devServer: {
    headers: { 'Access-Control-Allow-Origin': '*' },
    proxy: {
      '^/api': {
        target: 'http://127.0.0.1:9090',
        changeOrigin: true,
        ws:true,
        logLevel: 'debug',
        pathRewrite: { '^/api': '/api/' },
        timeout: 6000
      },
    },
  },
}
module.exports = {
  configureWebpack: {
    devServer: {
      headers: { 'Access-Control-Allow-Origin': '*' }
    }
  }
}