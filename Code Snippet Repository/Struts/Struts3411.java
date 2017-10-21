    public Class<?> loadClass(String className) throws ClassNotFoundException {
        Bundle bundle = getCurrentBundle();
        if (bundle != null) {
            Class cls = bundle.loadClass(className);
            LOG.trace("Located class [{}] in bundle [{}]", className, bundle.getSymbolicName());
            return cls;
        }

        throw new ClassNotFoundException("Unable to find class " + className);
    }
