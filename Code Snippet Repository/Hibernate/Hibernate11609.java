    @Override
    protected Class<?> findClass( String name ) throws ClassNotFoundException {
        log.trace("findClass(" + name + ")");
        Class result = classes.get(name);
        if (result != null) {
            return result;
        }

        if (isIncluded(name) && (isExcluded(name) == false)) {
            result = createClass(name);
        } else if (isNotFound(name)) {
            throw new ClassNotFoundException(name + " is discarded");
        } else {
            result = super.findClass(name);
        }

        classes.put(name, result);

        return result;
    }
