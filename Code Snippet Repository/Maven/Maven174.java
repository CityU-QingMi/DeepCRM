    protected void interpolateObject( Object obj, Model model, File projectDir, ProjectBuilderConfiguration config,
                                      boolean debugEnabled )
        throws ModelInterpolationException
    {
        try
        {
            List<ValueSource> valueSources = createValueSources( model, projectDir, config );
            List<InterpolationPostProcessor> postProcessors = createPostProcessors( model, projectDir, config );

            InterpolateObjectAction action =
                new InterpolateObjectAction( obj, valueSources, postProcessors, debugEnabled,
                                             this, getLogger() );

            ModelInterpolationException error = AccessController.doPrivileged( action );

            if ( error != null )
            {
                throw error;
            }
        }
        finally
        {
            getInterpolator().clearAnswers();
        }
    }
