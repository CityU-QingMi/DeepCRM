    protected void mergePlugin( Plugin target, Plugin source, boolean sourceDominant, Map<Object, Object> context )
    {
        mergeConfigurationContainer( target, source, sourceDominant, context );
        mergePlugin_GroupId( target, source, sourceDominant, context );
        mergePlugin_ArtifactId( target, source, sourceDominant, context );
        mergePlugin_Version( target, source, sourceDominant, context );
        mergePlugin_Extensions( target, source, sourceDominant, context );
        mergePlugin_Dependencies( target, source, sourceDominant, context );
        mergePlugin_Executions( target, source, sourceDominant, context );
    }
