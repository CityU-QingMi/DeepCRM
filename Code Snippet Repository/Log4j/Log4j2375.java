    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        final String path = name.replace('.', '/').concat(".class");
        final URL resource = super.getResource(path);
        if (resource == null) {
            throw new ClassNotFoundException(name);
        }
        try {
            final URLConnection uc = resource.openConnection();
            final int len = uc.getContentLength();
            final InputStream in = new BufferedInputStream(uc.getInputStream());
            final byte[] bytecode = new byte[len];
            try {
                IOUtils.readFully(in, bytecode);
            } finally {
                Closer.closeSilently(in);
            }
            return defineClass(name, bytecode, 0, bytecode.length);
        } catch (final IOException e) {
            Throwables.rethrow(e);
            return null; // unreachable
        }
    }
