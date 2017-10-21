    @Inject
    public void setContainer(Container container) {
        this.container = container;
        Map<String, EngineFactory> map = new HashMap<>();
        Set<String> prefixes = container.getInstanceNames(TemplateEngine.class);
        for (String prefix : prefixes) {
            map.put(prefix, new LazyEngineFactory(prefix));
        }
        this.templateEngines = Collections.unmodifiableMap(map);
    }
