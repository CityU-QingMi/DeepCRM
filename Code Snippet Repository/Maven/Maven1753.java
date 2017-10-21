    protected void mergeContributor( Contributor target, Contributor source, boolean sourceDominant,
                                     Map<Object, Object> context )
    {
        mergeContributor_Name( target, source, sourceDominant, context );
        mergeContributor_Email( target, source, sourceDominant, context );
        mergeContributor_Url( target, source, sourceDominant, context );
        mergeContributor_Organization( target, source, sourceDominant, context );
        mergeContributor_OrganizationUrl( target, source, sourceDominant, context );
        mergeContributor_Timezone( target, source, sourceDominant, context );
        mergeContributor_Roles( target, source, sourceDominant, context );
        mergeContributor_Properties( target, source, sourceDominant, context );
    }
