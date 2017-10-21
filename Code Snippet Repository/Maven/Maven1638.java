    protected void mergeBuildBase( BuildBase target, BuildBase source, boolean sourceDominant,
                                   Map<Object, Object> context )
    {
        mergePluginConfiguration( target, source, sourceDominant, context );
        mergeBuildBase_DefaultGoal( target, source, sourceDominant, context );
        mergeBuildBase_FinalName( target, source, sourceDominant, context );
        mergeBuildBase_Directory( target, source, sourceDominant, context );
        mergeBuildBase_Resources( target, source, sourceDominant, context );
        mergeBuildBase_TestResources( target, source, sourceDominant, context );
        mergeBuildBase_Filters( target, source, sourceDominant, context );
    }
