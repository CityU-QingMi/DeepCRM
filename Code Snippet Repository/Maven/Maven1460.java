    @Override
    public void normalize( Model model, ModelBuildingRequest request )
    {
        if ( model == null )
        {
            return;
        }

        model.setUrl( normalize( model.getUrl() ) );

        Scm scm = model.getScm();
        if ( scm != null )
        {
            scm.setUrl( normalize( scm.getUrl() ) );
            scm.setConnection( normalize( scm.getConnection() ) );
            scm.setDeveloperConnection( normalize( scm.getDeveloperConnection() ) );
        }

        DistributionManagement dist = model.getDistributionManagement();
        if ( dist != null )
        {
            Site site = dist.getSite();
            if ( site != null )
            {
                site.setUrl( normalize( site.getUrl() ) );
            }
        }
    }
