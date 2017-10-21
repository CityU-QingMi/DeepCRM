    protected void mergeReportPlugin_Version( ReportPlugin target, ReportPlugin source, boolean sourceDominant,
                                              Map<Object, Object> context )
    {
        String src = source.getVersion();
        if ( src != null )
        {
            if ( sourceDominant || target.getVersion() == null )
            {
                target.setVersion( src );
                target.setLocation( "version", source.getLocation( "version" ) );
            }
        }
    }
