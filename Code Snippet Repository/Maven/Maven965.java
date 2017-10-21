    private ProjectBuilder getProjectBuilder()
    {
        if ( projectBuilder != null )
        {
            return projectBuilder;
        }

        try
        {
            projectBuilder = container.lookup( ProjectBuilder.class );
        }
        catch ( ComponentLookupException e )
        {
            // Won't happen
        }

        return projectBuilder;
    }
