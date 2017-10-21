    private static File agentJar() {
        final String name = AllocationRecorder.class.getName();
        final URL url = AllocationRecorder.class.getResource("/" + name.replace('.', '/').concat(".class"));
        if (url == null) {
            throw new IllegalStateException("Could not find url for " + name);
        }
        final String temp = url.toString();
        final String path = temp.substring("jar:file:".length(), temp.indexOf('!'));
        return new File(path);
    }
