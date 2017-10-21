    public Map<String, String> mapAvailableStrings(String uri) throws IOException {
        resourcesNotLoaded.clear();
        Map<String, String> strings = new HashMap<>();
        Map<String, URL> resourcesMap = getResourcesMap(uri);
        for (Map.Entry<String, URL> entry  : resourcesMap.entrySet()) {
            String name = entry.getKey();
            URL url = entry.getValue();
            try {
                String value = readContents(url);
                strings.put(name, value);
            } catch (IOException notAvailable) {
                resourcesNotLoaded.add(url.toExternalForm());
            }
        }
        return strings;
    }
