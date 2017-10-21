    public boolean isDifferentPhase( MojoExecution nextMojoExecution )
    {
        String lifecyclePhase = nextMojoExecution.getLifecyclePhase();
        if ( lifecyclePhase == null )
        {
            return lastLifecyclePhase != null;
        }
        return !lifecyclePhase.equals( lastLifecyclePhase );

    }
