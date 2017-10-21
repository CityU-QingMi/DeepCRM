    public void testValueExtractionFromSystemPropertiesWithMissingProject_WithDotNotation()
        throws Exception
    {
        String sysprop = "PPEET.sysprop2";

        Properties executionProperties = new Properties();

        if ( executionProperties.getProperty( sysprop ) == null )
        {
            executionProperties.setProperty( sysprop, "value" );
        }

        ExpressionEvaluator ee = createExpressionEvaluator( null, null, executionProperties );

        Object value = ee.evaluate( "${" + sysprop + "}" );

        assertEquals( "value", value );
    }
