    private InputStream getMostSpecificStream(
            String key, String l, String c, String v) {
        final String filePath = baseName.replace('.', '/') + '/' + key
                + ((l == null) ? "" : ("_" + l))
                + ((c == null) ? "" : ("_" + c))
                + ((v == null) ? "" : ("_" + v))
                + ".text";
        // System.err.println("Seeking " + filePath);
        InputStream is = (InputStream) AccessController.doPrivileged(
            new PrivilegedAction() {

            public InputStream run() {
                return loader.getResourceAsStream(filePath);
            }
        });
        // N.b.  If were using Class.getRes... instead of ClassLoader.getRes...
        // we would need to prefix the path with "/".
        return (is == null && l != null)
            ? getMostSpecificStream(key, ((c == null) ? null : l),
                    ((v == null) ? null : c), null)
            : is;
    }
