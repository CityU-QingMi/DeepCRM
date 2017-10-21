    private void assembleReportingInheritance( Model child, Model parent )
    {
        // Reports :: aggregate
        Reporting childReporting = child.getReporting();
        Reporting parentReporting = parent.getReporting();

        if ( parentReporting != null )
        {
            if ( childReporting == null )
            {
                childReporting = new Reporting();
                child.setReporting( childReporting );
            }

            childReporting.setExcludeDefaults( parentReporting.isExcludeDefaults() );

            if ( StringUtils.isEmpty( childReporting.getOutputDirectory() ) )
            {
                childReporting.setOutputDirectory( parentReporting.getOutputDirectory() );
            }

            mergeReportPluginLists( childReporting, parentReporting, true );
        }
    }
