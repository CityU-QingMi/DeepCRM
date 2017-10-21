    private void addMojoExecution( Map<Integer, List<MojoExecution>> phaseBindings, MojoExecution mojoExecution,
                                   int priority )
    {
        List<MojoExecution> mojoExecutions = phaseBindings.get( priority );

        if ( mojoExecutions == null )
        {
            mojoExecutions = new ArrayList<>();
            phaseBindings.put( priority, mojoExecutions );
        }

        mojoExecutions.add( mojoExecution );
    }
