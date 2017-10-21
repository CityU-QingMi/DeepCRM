    public boolean putPropertiesFromFile(String path, String extension) {

        if (getState() != ServerConstants.SERVER_STATE_SHUTDOWN) {
            throw Error.error(ErrorCode.GENERAL_ERROR, "server properties");
        }

        path = FileUtil.getFileUtil().canonicalOrAbsolutePath(path);

        ServerProperties p = ServerConfiguration.getPropertiesFromFile(
            ServerConstants.SC_PROTOCOL_HSQL, path, extension);

        if (p == null || p.isEmpty()) {
            return false;
        }

        printWithThread("putPropertiesFromFile(): [" + path + ".properties]");

        try {
            setProperties(p);
        } catch (Exception e) {
            throw Error.error(e, ErrorCode.GENERAL_ERROR,
                              ErrorCode.M_Message_Pair,
                              new String[]{ "Failed to set properties" });
        }

        return true;
    }
