    public void testPreAndPostProcessing()
        throws BeanConfigurationException
    {
        SomeBean bean = new SomeBean();

        Xpp3Dom config = toConfig( "<file>${test}</file>" );

        BeanConfigurationValuePreprocessor preprocessor = new BeanConfigurationValuePreprocessor()
        {
            public Object preprocessValue( String value, Class<?> type )
                throws BeanConfigurationException
            {
                if ( value != null && value.startsWith( "${" ) && value.endsWith( "}" ) )
                {
                    return value.substring( 2, value.length() - 1 );
                }
                return value;
            }
        };

        BeanConfigurationPathTranslator translator = new BeanConfigurationPathTranslator()
        {
            public File translatePath( File path )
            {
                return new File( "base", path.getPath() ).getAbsoluteFile();
            }
        };

        DefaultBeanConfigurationRequest request = new DefaultBeanConfigurationRequest();
        request.setBean( bean ).setConfiguration( config );
        request.setValuePreprocessor( preprocessor ).setPathTranslator( translator );

        configurator.configureBean( request );

        assertEquals( new File( "base/test" ).getAbsoluteFile(), bean.file );
    }
