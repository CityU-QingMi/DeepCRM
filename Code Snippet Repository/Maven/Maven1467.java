    private Xpp3Dom convert( ReportPlugin plugin )
    {
        Xpp3Dom dom = new Xpp3Dom( "reportPlugin" );

        addDom( dom, "groupId", plugin.getGroupId() );
        addDom( dom, "artifactId", plugin.getArtifactId() );
        addDom( dom, "version", plugin.getVersion() );

        Xpp3Dom configuration = (Xpp3Dom) plugin.getConfiguration();
        if ( configuration != null )
        {
            configuration = new Xpp3Dom( configuration );
            dom.addChild( configuration );
        }

        if ( !plugin.getReportSets().isEmpty() )
        {
            Xpp3Dom reportSets = new Xpp3Dom( "reportSets" );
            for ( ReportSet reportSet : plugin.getReportSets() )
            {
                Xpp3Dom rs = convert( reportSet );
                reportSets.addChild( rs );
            }
            dom.addChild( reportSets );
        }

        return dom;
    }
