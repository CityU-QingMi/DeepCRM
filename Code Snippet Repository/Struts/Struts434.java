    public List<URL> findAll(String uri) throws IOException {
        String fullUri = path + uri;

        Enumeration<URL> resources = getResources(fullUri);
        List<URL> list = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            list.add(url);
        }
        return list;
    }
