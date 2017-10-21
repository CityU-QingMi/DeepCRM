    protected void openConnection(String host, int port, boolean isTLS) {

        try {
            URL    url = null;
            String s   = "";

            if (!path.endsWith("/")) {
                s = "/";
            }

            s = "http://" + host + ":" + port + path + s + database;

            if (isTLS) {
                url = new URL("https://" + host + ":" + port + path + s
                              + database);    // PROTECT/servlet/hsqldb
            } else {
                url = new URL(s);             // PROTECT/servlet/hsqldb
            }

            httpConnection = (HttpURLConnection) url.openConnection();

            httpConnection.setDefaultUseCaches(false);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
