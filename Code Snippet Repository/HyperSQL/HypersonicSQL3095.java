    private static HsqlSocketFactory getSSLImpl() throws Exception {

        synchronized (HsqlSocketFactory.class) {
            if (sslImpl == null) {
                sslImpl =
                    newFactory("org.hsqldb.server.HsqlSocketFactorySecure");
            }
        }

        return sslImpl;
    }
