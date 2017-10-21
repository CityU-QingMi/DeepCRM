    private static HsqlSocketFactory getPlainImpl() throws Exception {

        synchronized (HsqlSocketFactory.class) {
            if (plainImpl == null) {
                plainImpl = new HsqlSocketFactory();
            }
        }

        return plainImpl;
    }
