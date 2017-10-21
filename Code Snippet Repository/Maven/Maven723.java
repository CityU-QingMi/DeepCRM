    public void observeExecution( MojoExecution mojoExecution )
    {
        String lifecyclePhase = mojoExecution.getLifecyclePhase();

        if ( lifecyclePhase != null )
        {
            if ( lastLifecyclePhase == null )
            {
                lastLifecyclePhase = lifecyclePhase;
            }
            else if ( !lifecyclePhase.equals( lastLifecyclePhase ) )
            {
                project.addLifecyclePhase( lastLifecyclePhase );
                lastLifecyclePhase = lifecyclePhase;
            }
        }

        if ( lastLifecyclePhase != null )
        {
            project.addLifecyclePhase( lastLifecyclePhase );
        }
    }
