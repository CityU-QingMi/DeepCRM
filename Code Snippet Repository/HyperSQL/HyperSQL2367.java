    public void setWebRoot(String root) {

        checkRunning(false);

        root = (new File(root)).getAbsolutePath();

        printWithThread("setWebRoot(" + root + ")");

        if (serverProtocol != ServerConstants.SC_PROTOCOL_HTTP) {
            return;
        }

        serverProperties.setProperty(ServerProperties.sc_key_web_root, root);
    }
