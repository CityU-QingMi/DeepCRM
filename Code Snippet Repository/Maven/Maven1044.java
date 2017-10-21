    public void testChildConfigurationElement()
        throws BeanConfigurationException
    {
        SomeBean bean = new SomeBean();

        Xpp3Dom config = toConfig( "<wrapper><file>test</file></wrapper>" );

        DefaultBeanConfigurationRequest request = new DefaultBeanConfigurationRequest();
        request.setBean( bean ).setConfiguration( config, "wrapper" );

        configurator.configureBean( request );

        assertEquals( new File( "test" ), bean.file );
    }
