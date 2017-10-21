    @Before
    public void init() throws Exception
    {
        // Jetty's XML configuration will make use of java.util.ServiceLoader
        // to load the proper ConfigurationProcessorFactory, so these tests
        // will always fail in JDK 5.

        String javaVersion = System.getProperty("java.version");
        Pattern regexp = Pattern.compile("1\\.(\\d{1})\\..*");
        Matcher matcher = regexp.matcher(javaVersion);
        if (matcher.matches())
        {
            String minor = matcher.group(1);
            assumeTrue(Integer.parseInt(minor) > 5);
        }
    }
