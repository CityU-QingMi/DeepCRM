    @Override
    public void expandPluginConfiguration( Model model, ModelBuildingRequest request, ModelProblemCollector problems )
    {
        Reporting reporting = model.getReporting();

        if ( reporting != null )
        {
            for ( ReportPlugin reportPlugin : reporting.getPlugins() )
            {
                Xpp3Dom parentDom = (Xpp3Dom) reportPlugin.getConfiguration();

                if ( parentDom != null )
                {
                    for ( ReportSet execution : reportPlugin.getReportSets() )
                    {
                        Xpp3Dom childDom = (Xpp3Dom) execution.getConfiguration();
                        childDom = Xpp3Dom.mergeXpp3Dom( childDom, new Xpp3Dom( parentDom ) );
                        execution.setConfiguration( childDom );
                    }
                }
            }
        }
    }
