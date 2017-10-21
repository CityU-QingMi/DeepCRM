    public AttributeNormalizerTest(String arch, String title, Map<String, String> env) throws IOException
    {
        this.arch = arch;
        this.title = title;
        this.env = env;
        
        // Remember old values
        env.keySet().stream().forEach((key) ->
        {
            String old = System.getProperty(key);
            oldValues.put(key, old);
        });
        
        // Grab specific values of interest in general
        jettyHome = env.get("jetty.home");
        jettyBase = env.get("jetty.base");
        war = env.get("WAR");
        
        // Set environment (skipping null and WAR)
        env.entrySet().stream()
                .filter((e) -> e.getValue() != null && !e.getKey().equalsIgnoreCase("WAR"))
                .forEach((entry) -> System.setProperty(entry.getKey(), entry.getValue()));
        
        // Setup normalizer
        Resource webresource = Resource.newResource(war);
        this.normalizer = new AttributeNormalizer(webresource);
    }
