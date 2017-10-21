    public String interpolate( String src,
                               Model model,
                               final File projectDir,
                               ProjectBuilderConfiguration config,
                               boolean debug )
        throws ModelInterpolationException
    {
        try
        {
            List<ValueSource> valueSources = createValueSources( model, projectDir, config );
            List<InterpolationPostProcessor> postProcessors = createPostProcessors( model, projectDir, config );

            return interpolateInternal( src, valueSources, postProcessors, debug );
        }
        finally
        {
            interpolator.clearAnswers();
        }
    }
