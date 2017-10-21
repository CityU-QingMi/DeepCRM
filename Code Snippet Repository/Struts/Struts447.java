    public Map<String, String> mapAllStrings(String uri) throws IOException {
        Map<String, String> strings = new HashMap<>();
        Map<String, URL> resourcesMap = getResourcesMap(uri);
        for (Map.Entry<String, URL> entry : resourcesMap.entrySet()) {
            String name = entry.getKey();
            URL url = entry.getValue();
            String value = readContents(url);
            strings.put(name, value);
        }
        return strings;
    }
