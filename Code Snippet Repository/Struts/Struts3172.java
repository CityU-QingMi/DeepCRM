    protected static Method lookupMethodByName(Class clazz, String name) {
        Method[] methods = clazz.getMethods();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().equals(name)) {
                return methods[i];
            }
        }
        throw new RuntimeException("No " + name + "(...) method found for "
                + clazz.getName() + ".");
    }
