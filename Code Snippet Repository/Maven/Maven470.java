    @Override
    public MavenExecutionResult execute( MavenExecutionRequest request )
    {
        MavenExecutionResult result;

        try
        {
            result = doExecute( request );
        }
        catch ( OutOfMemoryError e )
        {
            result = addExceptionToResult( new DefaultMavenExecutionResult(), e );
        }
        catch ( RuntimeException e )
        {
            // TODO Hack to make the cycle detection the same for the new graph builder
            if ( e.getCause() instanceof ProjectCycleException )
            {
                result = addExceptionToResult( new DefaultMavenExecutionResult(), e.getCause() );
            }
            else
            {
                result = addExceptionToResult( new DefaultMavenExecutionResult(),
                                               new InternalErrorException( "Internal error: " + e, e ) );
            }
        }
        finally
        {
            legacySupport.setSession( null );
        }

        return result;
    }
