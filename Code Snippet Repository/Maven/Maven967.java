    private Properties getSystemProperties()
    {
        Properties props = new Properties();

        EnvironmentUtils.addEnvVars( props );

        SystemProperties.addSystemProperties( props );

        return props;
    }
