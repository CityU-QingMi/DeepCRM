    @Override
    public List<ProjectBuildingResult> build( List<File> pomFiles, boolean recursive, ProjectBuildingRequest request )
        throws ProjectBuildingException
    {
        List<ProjectBuildingResult> results = new ArrayList<>();

        List<InterimResult> interimResults = new ArrayList<>();

        ReactorModelPool modelPool = new ReactorModelPool();

        ReactorModelCache modelCache = new ReactorModelCache();

        InternalConfig config = new InternalConfig( request, modelPool, modelCache );

        Map<String, MavenProject> projectIndex = new HashMap<>( 256 );

        boolean noErrors =
            build( results, interimResults, projectIndex, pomFiles, new LinkedHashSet<File>(), true, recursive,
                   config );

        populateReactorModelPool( modelPool, interimResults );

        ClassLoader oldContextClassLoader = Thread.currentThread().getContextClassLoader();

        try
        {
            noErrors =
                build( results, new ArrayList<MavenProject>(), projectIndex, interimResults, request,
                       new HashMap<File, Boolean>() ) && noErrors;
        }
        finally
        {
            Thread.currentThread().setContextClassLoader( oldContextClassLoader );
        }

        if ( !noErrors )
        {
            throw new ProjectBuildingException( results );
        }

        return results;
    }
