    public HsqlProperties getClientProperties() {

        if (clientProperties == null) {
            if (clientPropertiesString.length() > 0) {
                clientProperties = HsqlProperties.delimitedArgPairsToProps(
                    clientPropertiesString, "=", ";", null);
            } else {
                clientProperties = new HsqlProperties();
            }
        }

        return clientProperties;
    }
