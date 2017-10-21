    @Override
    protected synchronized Class<?> loadClass( String name,
                                               boolean resolve ) throws ClassNotFoundException {
        log.trace("loadClass(" + name + "," + resolve + ")");
        if (isIncluded(name) && (isExcluded(name) == false)) {
            Class c = findClass(name);

            if (resolve) {
                resolveClass(c);
            }
            return c;
        } else if (isNotFound(name)) {
            throw new ClassNotFoundException(name + " is discarded");
        } else {
            return super.loadClass(name, resolve);
        }
    }
