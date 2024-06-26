const { override, adjustStyleLoaders } = require('customize-cra');

module.exports = {
    webpack: override(
        // 추가 Webpack 설정들
    ),
    devServer: (configFunction) => (proxy, allowedHost) => {
        const config = configFunction(proxy, allowedHost);
        config.allowedHosts = ['localhost'];
        return config;
    },
};
