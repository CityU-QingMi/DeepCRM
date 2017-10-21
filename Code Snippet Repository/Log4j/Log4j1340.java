    private static SSLSocketFactory createSslSocketFactory(final SslConfiguration sslConf) {
        SSLSocketFactory socketFactory;

        if (sslConf != null) {
            socketFactory = sslConf.getSslSocketFactory();
        } else {
            socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        }

        return socketFactory;
    }
