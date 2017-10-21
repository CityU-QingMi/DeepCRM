    public synchronized Class<?> loadClass(String className) throws ClassNotFoundException
    {
        if (className == null)
            return null;

        if (_classLoader == null)
            return Loader.loadClass(className);

        return _classLoader.loadClass(className);
    }
