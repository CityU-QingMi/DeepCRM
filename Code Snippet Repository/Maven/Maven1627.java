    protected void mergeBuild( Build target, Build source, boolean sourceDominant, Map<Object, Object> context )
    {
        mergeBuildBase( target, source, sourceDominant, context );
        mergeBuild_SourceDirectory( target, source, sourceDominant, context );
        mergeBuild_ScriptSourceDirectory( target, source, sourceDominant, context );
        mergeBuild_TestSourceDirectory( target, source, sourceDominant, context );
        mergeBuild_OutputDirectory( target, source, sourceDominant, context );
        mergeBuild_TestOutputDirectory( target, source, sourceDominant, context );
        mergeBuild_Extensions( target, source, sourceDominant, context );
    }
