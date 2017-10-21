    @Parameterized.Parameters(name = "")
    public static Object[][] getParameters() {
        // @formatter:off
        return new Object[][] { 
                { "log4j-routing-default-route-script-groovy.xml", false },
                { "log4j-routing-default-route-script-javascript.xml", false },
                { "log4j-routing-script-staticvars-javascript.xml", true },
                { "log4j-routing-script-staticvars-groovy.xml", true },
        };
        // @formatter:on
    }
