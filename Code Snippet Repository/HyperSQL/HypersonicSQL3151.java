    public void putPropertiesFromString(String s) {

        if (getState() != ServerConstants.SERVER_STATE_SHUTDOWN) {
            throw Error.error(ErrorCode.GENERAL_ERROR);
        }

        if (StringUtil.isEmpty(s)) {
            return;
        }

        printWithThread("putPropertiesFromString(): [" + s + "]");

        HsqlProperties p = HsqlProperties.delimitedArgPairsToProps(s, "=",
            ";", ServerProperties.sc_key_prefix);

        try {
            setProperties(p);
        } catch (Exception e) {
            throw Error.error(e, ErrorCode.GENERAL_ERROR,
                              ErrorCode.M_Message_Pair,
                              new String[]{ "Failed to set properties" });
        }
    }
