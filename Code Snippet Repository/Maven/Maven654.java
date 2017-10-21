    @Override
    public Result<ProjectDependencyGraph> build( MavenSession session )
    {
        try
        {
            Result<ProjectDependencyGraph> result = sessionDependencyGraph( session );

            if ( result == null )
            {
                final List<MavenProject> projects = getProjectsForMavenReactor( session );
                validateProjects( projects );
                result = reactorDependencyGraph( session, projects );
            }

            return result;
        }
        catch ( final ProjectBuildingException e )
        {
            return Result.error( Lists.newArrayList( new DefaultModelProblem( null, null, null, null, 0, 0, e ) ) );
        }
        catch ( final CycleDetectedException e )
        {
            String message = "The projects in the reactor contain a cyclic reference: " + e.getMessage();
            ProjectCycleException error = new ProjectCycleException( message, e );
            return Result.error( Lists.newArrayList( new DefaultModelProblem( null, null, null, null, 0, 0, error ) ) );
        }
        catch ( final DuplicateProjectException e )
        {
            return Result.error( Lists.newArrayList( new DefaultModelProblem( null, null, null, null, 0, 0, e ) ) );
        }
        catch ( final MavenExecutionException e )
        {
            return Result.error( Lists.newArrayList( new DefaultModelProblem( null, null, null, null, 0, 0, e ) ) );
        }
    }
