    public Map<String, Class> mapAllClasses(String uri) throws IOException, ClassNotFoundException {
        Map<String, Class> classes = new HashMap<>();
        Map<String, String> map = mapAllStrings(uri);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String string = entry.getKey();
            String className = entry.getValue();
            Class clazz = classLoaderInterface.loadClass(className);
            classes.put(string, clazz);
        }
        return classes;
    }
