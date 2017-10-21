    public void testMinimal()
        throws BeanConfigurationException
    {
        SomeBean bean = new SomeBean();

        Xpp3Dom config = toConfig( "<file>test</file>" );

        DefaultBeanConfigurationRequest request = new DefaultBeanConfigurationRequest();
        request.setBean( bean ).setConfiguration( config );

        configurator.configureBean( request );

        assertEquals( new File( "test" ), bean.file );
    }
