    private Enumeration<URL> getResources(String fulluri) throws IOException {
        if (urls == null) {
            return classLoaderInterface.getResources(fulluri);
        }
        Vector<URL> resources = new Vector<>();
        for (URL url : urls) {
            URL resource = findResource(fulluri, url);
            if (resource != null){
                resources.add(resource);
            }
        }
        return resources.elements();
    }
