    private static void mergeReportSetDefinitions( ReportSet child, ReportSet parent )
    {
        List<String> parentReports = parent.getReports();
        List<String> childReports = child.getReports();

        List<String> reports = new ArrayList<>();

        if ( ( childReports != null ) && !childReports.isEmpty() )
        {
            reports.addAll( childReports );
        }

        if ( parentReports != null )
        {
            for ( String report : parentReports )
            {
                if ( !reports.contains( report ) )
                {
                    reports.add( report );
                }
            }
        }

        child.setReports( reports );

        Xpp3Dom childConfiguration = (Xpp3Dom) child.getConfiguration();
        Xpp3Dom parentConfiguration = (Xpp3Dom) parent.getConfiguration();

        childConfiguration = Xpp3Dom.mergeXpp3Dom( childConfiguration, parentConfiguration );

        child.setConfiguration( childConfiguration );
    }
