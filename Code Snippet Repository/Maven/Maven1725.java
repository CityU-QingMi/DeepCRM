    protected void mergeReporting_Plugins( Reporting target, Reporting source, boolean sourceDominant,
                                           Map<Object, Object> context )
    {
        List<ReportPlugin> src = source.getPlugins();
        if ( !src.isEmpty() )
        {
            List<ReportPlugin> tgt = target.getPlugins();
            Map<Object, ReportPlugin> merged =
                new LinkedHashMap<>( ( src.size() + tgt.size() ) * 2 );

            for ( ReportPlugin element : tgt )
            {
                Object key = getReportPluginKey( element );
                merged.put( key, element );
            }

            for ( ReportPlugin element : src )
            {
                Object key = getReportPluginKey( element );
                if ( sourceDominant || !merged.containsKey( key ) )
                {
                    merged.put( key, element );
                }
            }

            target.setPlugins( new ArrayList<>( merged.values() ) );
        }
    }
