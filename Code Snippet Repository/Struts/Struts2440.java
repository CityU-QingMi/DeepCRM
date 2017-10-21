    public List<Properties> getJarProperties() throws IOException {
        ResolverUtil resolver = new ResolverUtil();
        List<Properties> poms = new ArrayList<Properties>();
        resolver.findNamedResource("pom.properties", "META-INF/maven");
        Set<URL> urls = resolver.getResources();
        for (URL url : urls) {
            Properties p = new Properties();
            p.load(url.openStream());
            poms.add(p);
        }
        return poms;
    }
