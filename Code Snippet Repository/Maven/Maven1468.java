    private Xpp3Dom convert( ReportSet reportSet )
    {
        Xpp3Dom dom = new Xpp3Dom( "reportSet" );

        addDom( dom, "id", reportSet.getId() );

        Xpp3Dom configuration = (Xpp3Dom) reportSet.getConfiguration();
        if ( configuration != null )
        {
            configuration = new Xpp3Dom( configuration );
            dom.addChild( configuration );
        }

        if ( !reportSet.getReports().isEmpty() )
        {
            Xpp3Dom reports = new Xpp3Dom( "reports" );
            for ( String report : reportSet.getReports() )
            {
                addDom( reports, "report", report );
            }
            dom.addChild( reports );
        }

        return dom;
    }
