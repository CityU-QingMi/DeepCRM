    @Deprecated
    public List<ReportPlugin> getReportPlugins()
    {
        if ( getModel().getReporting() == null )
        {
            return Collections.emptyList();
        }
        return getModel().getReporting().getPlugins();

    }
