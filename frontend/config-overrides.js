const { override, addWebpackPlugin } = require('customize-cra');
const webpack = require('webpack');

module.exports = {
    webpack: override(
        addWebpackPlugin(
            new webpack.ProvidePlugin({
                process: 'process/browser',
            })
        ),
        config => {
            config.resolve.fallback = {
                ...config.resolve.fallback,
                "process": require.resolve("process/browser"),
            };
            return config;
        }
    ),
    devServer: (configFunction) => (proxy, allowedHost) => {
        const config = configFunction(proxy, allowedHost);
        config.allowedHosts = ['localhost'];
        return config;
    },
};
