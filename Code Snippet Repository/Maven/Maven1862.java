    public DefaultSettingsBuilder newInstance()
    {
        DefaultSettingsBuilder builder = new DefaultSettingsBuilder();

        builder.setSettingsReader( newSettingsReader() );
        builder.setSettingsWriter( newSettingsWriter() );
        builder.setSettingsValidator( newSettingsValidator() );

        return builder;
    }
