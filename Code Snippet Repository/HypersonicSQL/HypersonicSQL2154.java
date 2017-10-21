    public RCData(String id, String url, String username, String password,
                  String driver, String charset, String truststore,
                  String libpath, String ti) throws Exception {

        this.id         = id;
        this.url        = url;
        this.username   = username;
        this.password   = password;
        this.ti         = ti;
        this.driver     = driver;
        this.charset    = charset;
        this.truststore = truststore;
        this.libpath    = libpath;

        if (libpath != null) {
            throw new IllegalArgumentException(
                "Sorry, 'libpath' not supported yet");
        }

        if (id == null || url == null) {
            throw new Exception("id or url was not set");
        }
    }
