    public void setDefaultWebPage(String file) {

        checkRunning(false);
        printWithThread("setDefaultWebPage(" + file + ")");

        if (serverProtocol != ServerConstants.SC_PROTOCOL_HTTP) {
            return;
        }

        serverProperties.setProperty(ServerProperties.sc_key_web_default_page,
                                     file);
    }
