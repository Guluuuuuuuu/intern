module.exports = {
  devServer: {
    port: 8080,
    proxy: {
      '/api': {
        target: 'http://localhost:8889',
        changeOrigin: true
      }
    }
  }
} 