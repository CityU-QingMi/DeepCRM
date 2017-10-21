    protected void mergeReportSet_Reports( ReportSet target, ReportSet source, boolean sourceDominant,
                                           Map<Object, Object> context )
    {
        List<String> src = source.getReports();
        if ( !src.isEmpty() )
        {
            List<String> tgt = target.getReports();
            List<String> merged = new ArrayList<>( tgt.size() + src.size() );
            merged.addAll( tgt );
            merged.addAll( src );
            target.setReports( merged );
        }
    }
