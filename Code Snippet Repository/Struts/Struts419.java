    private Class fastFindClass(final String name) {

        if (stores != null) {
            String fileName = name.replace('.', '/') + ".class";
            for (final ResourceStore store : stores) {
                final byte[] clazzBytes = store.read(fileName);
                if (clazzBytes != null) {
                    definePackage(name);
                    return defineClass(name, clazzBytes, 0, clazzBytes.length);
                }
            }
        }

        return null;
    }
