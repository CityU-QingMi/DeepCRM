    @Configuration
    public Option[] config()
    {
        VersionResolver resolver = MavenUtils.asInProject();
        ArrayList<Option> options = new ArrayList<Option>();
        options.addAll(provisionCoreJetty());
        options.add(CoreOptions.junitBundles());
        options.addAll(httpServiceJetty());
        options.addAll(Arrays.asList(options(systemProperty("pax.exam.logging").value("none"))));
        options.addAll(Arrays.asList(options(systemProperty("org.ops4j.pax.logging.DefaultServiceLog.level").value(LOG_LEVEL))));
        options.addAll(Arrays.asList(options(systemProperty("org.eclipse.jetty.LEVEL").value(LOG_LEVEL))));
        options.addAll(Arrays.asList(options(systemProperty("org.eclipse.jetty.annotations.LEVEL").value("DEBUG"))));
        return options.toArray(new Option[options.size()]);
    }
