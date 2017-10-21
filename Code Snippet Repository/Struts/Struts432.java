    public Map<String, Properties> mapAllProperties(String uri) throws IOException {
        Map<String, Properties> propertiesMap = new HashMap<>();
        Map<String, URL> map = getResourcesMap(uri);
        for (Map.Entry<String, URL> entry : map.entrySet()) {
            String string = entry.getKey();
            URL url = entry.getValue();
            Properties properties = loadProperties(url);
            propertiesMap.put(string, properties);
        }
        return propertiesMap;
    }
