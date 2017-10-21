    protected void mergeDependency( Dependency target, Dependency source, boolean sourceDominant,
                                    Map<Object, Object> context )
    {
        mergeDependency_GroupId( target, source, sourceDominant, context );
        mergeDependency_ArtifactId( target, source, sourceDominant, context );
        mergeDependency_Version( target, source, sourceDominant, context );
        mergeDependency_Type( target, source, sourceDominant, context );
        mergeDependency_Classifier( target, source, sourceDominant, context );
        mergeDependency_Scope( target, source, sourceDominant, context );
        mergeDependency_SystemPath( target, source, sourceDominant, context );
        mergeDependency_Optional( target, source, sourceDominant, context );
        mergeDependency_Exclusions( target, source, sourceDominant, context );
    }
