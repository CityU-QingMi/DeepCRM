    public void setAddress(String address) {

        checkRunning(false);

        if (StringUtil.isEmpty(address)) {
            address = ServerConstants.SC_DEFAULT_ADDRESS;
        }

        printWithThread("setAddress(" + address + ")");
        serverProperties.setProperty(ServerProperties.sc_key_address, address);
    }
