    public static List<Option> provisionCoreJetty()
    { 
        List<Option> res = new ArrayList<Option>();
        // get the jetty home config from the osgi boot bundle.
        res.add(CoreOptions.systemProperty("jetty.http.port").value(String.valueOf(DEFAULT_HTTP_PORT)));
        res.add(CoreOptions.systemProperty("jetty.ssl.port").value(String.valueOf(DEFAULT_SSL_PORT)));
        res.add(CoreOptions.systemProperty("jetty.home.bundle").value("org.eclipse.jetty.osgi.boot"));
        res.addAll(coreJettyDependencies());
        return res;
    }
