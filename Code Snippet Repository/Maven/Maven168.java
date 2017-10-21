    public Model interpolate( Model model, Map<String, ?> context, boolean strict )
        throws ModelInterpolationException
    {
        Properties props = new Properties();
        props.putAll( context );

        return interpolate( model,
                            null,
                            new DefaultProjectBuilderConfiguration().setExecutionProperties( props ),
                            true );
    }
