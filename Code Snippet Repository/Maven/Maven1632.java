    protected void mergeBuild_OutputDirectory( Build target, Build source, boolean sourceDominant,
                                               Map<Object, Object> context )
    {
        String src = source.getOutputDirectory();
        if ( src != null )
        {
            if ( sourceDominant || target.getOutputDirectory() == null )
            {
                target.setOutputDirectory( src );
                target.setLocation( "outputDirectory", source.getLocation( "outputDirectory" ) );
            }
        }
    }
