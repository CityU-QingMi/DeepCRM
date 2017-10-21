    public static ServerProperties getPropertiesFromFile(int protocol,
            String path, String extension) {

        boolean result;

        if (StringUtil.isEmpty(path)) {
            return null;
        }

        ServerProperties p = new ServerProperties(protocol, path, extension);

        try {
            result = p.load();
        } catch (Exception e) {
            return null;
        }

        return result ? p
                      : null;
    }
