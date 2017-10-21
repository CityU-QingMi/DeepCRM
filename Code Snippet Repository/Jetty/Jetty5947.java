    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException
    {
        if (_transformers.isEmpty())
            return super.findClass(name);

        String path = name.replace('.', '/').concat(".class");
        URL url = findResource(path);
        if (url==null)
            throw new ClassNotFoundException(name);
        return foundClass(name,url);
    }
