    public TestBase(String name, String url, boolean isNetwork,
                    boolean isHTTP) {

        super(name);

        if (url != null) {
            this.url = url;
        }

        this.isNetwork = isNetwork;
        this.isHTTP    = isHTTP;
    }
