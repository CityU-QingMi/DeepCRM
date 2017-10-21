    public void releaseMojo( Object mojo, MojoExecution mojoExecution )
    {
        if ( mojo != null )
        {
            try
            {
                container.release( mojo );
            }
            catch ( ComponentLifecycleException e )
            {
                String goalExecId = mojoExecution.getGoal();

                if ( mojoExecution.getExecutionId() != null )
                {
                    goalExecId += " {execution: " + mojoExecution.getExecutionId() + "}";
                }

                logger.debug( "Error releasing mojo for " + goalExecId, e );
            }
        }
    }
