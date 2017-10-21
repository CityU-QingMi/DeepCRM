    private String createClassPath(final Class<?> cls) throws Exception {
        final String resource = "/" + cls.getName().replace('.', '/') + ".class";
        final URL url = cls.getResource(resource);
        String location = url.toString();
        if (location.startsWith("jar:")) {
            location = location.substring("jar:".length(), location.indexOf('!'));
        }
        if (location.startsWith("file:/")) {
            location = location.substring("file:/".length());
        }
        if (location.endsWith(resource)) {
            location = location.substring(0, location.length() - resource.length());
        }
        if (!new File(location).exists()) {
            location = File.separator + location;
        }
        location = URLDecoder.decode(location, Charset.defaultCharset().name()); // replace %20 with ' ' etc
        return location.isEmpty() ? "." : location;
    }
