    protected void mergeModel( Model target, Model source, boolean sourceDominant, Map<Object, Object> context )
    {
        mergeModelBase( target, source, sourceDominant, context );

        mergeModel_ModelVersion( target, source, sourceDominant, context );
        mergeModel_Parent( target, source, sourceDominant, context );
        mergeModel_GroupId( target, source, sourceDominant, context );
        mergeModel_ArtifactId( target, source, sourceDominant, context );
        mergeModel_Version( target, source, sourceDominant, context );
        mergeModel_Packaging( target, source, sourceDominant, context );
        mergeModel_Name( target, source, sourceDominant, context );
        mergeModel_Description( target, source, sourceDominant, context );
        mergeModel_Url( target, source, sourceDominant, context );
        mergeModel_InceptionYear( target, source, sourceDominant, context );
        mergeModel_Organization( target, source, sourceDominant, context );
        mergeModel_Licenses( target, source, sourceDominant, context );
        mergeModel_MailingLists( target, source, sourceDominant, context );
        mergeModel_Developers( target, source, sourceDominant, context );
        mergeModel_Contributors( target, source, sourceDominant, context );
        mergeModel_IssueManagement( target, source, sourceDominant, context );
        mergeModel_Scm( target, source, sourceDominant, context );
        mergeModel_CiManagement( target, source, sourceDominant, context );
        mergeModel_Prerequisites( target, source, sourceDominant, context );
        mergeModel_Build( target, source, sourceDominant, context );
        mergeModel_Profiles( target, source, sourceDominant, context );
    }
