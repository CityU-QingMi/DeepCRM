    protected String buildPath(String name, String packagePrefix) throws UnsupportedEncodingException {
        String resourcePath;
        if (packagePrefix.endsWith("/") && name.startsWith("/")) {
            resourcePath = packagePrefix + name.substring(1);
        } else {
            resourcePath = packagePrefix + name;
        }

        return URLDecoder.decode(resourcePath, encoding);
    }
