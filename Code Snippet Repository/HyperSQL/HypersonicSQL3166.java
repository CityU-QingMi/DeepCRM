    public static void translateAddressProperty(HsqlProperties p) {

        if (p == null) {
            return;
        }

        String address = p.getProperty(ServerProperties.sc_key_address);

        if (StringUtil.isEmpty(address)) {
            p.setProperty(ServerProperties.sc_key_address, SC_DEFAULT_ADDRESS);
        }
    }
