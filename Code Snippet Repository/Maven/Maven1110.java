    public void testValueExtractionFromSystemPropertiesWithMissingProject()
        throws Exception
    {
        String sysprop = "PPEET_sysprop1";

        Properties executionProperties = new Properties();

        if ( executionProperties.getProperty( sysprop ) == null )
        {
            executionProperties.setProperty( sysprop, "value" );
        }

        ExpressionEvaluator ee = createExpressionEvaluator( null, null, executionProperties );

        Object value = ee.evaluate( "${" + sysprop + "}" );

        assertEquals( "value", value );
    }
