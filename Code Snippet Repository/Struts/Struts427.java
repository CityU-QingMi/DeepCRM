    public Map<String, Class> mapAllImplementations(Class interfase) throws IOException, ClassNotFoundException {
        Map<String, Class> implementations = new HashMap<>();
        Map<String, String> map = mapAllStrings(interfase.getName());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String string = entry.getKey();
            String className = entry.getValue();
            Class impl = classLoaderInterface.loadClass(className);
            if (!interfase.isAssignableFrom(impl)) {
                throw new ClassCastException("Class not of type: " + interfase.getName());
            }
            implementations.put(string, impl);
        }
        return implementations;
    }
