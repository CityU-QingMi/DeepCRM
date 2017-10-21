    protected void mergeDependency_Classifier( Dependency target, Dependency source, boolean sourceDominant,
                                               Map<Object, Object> context )
    {
        String src = source.getClassifier();
        if ( src != null )
        {
            if ( sourceDominant || target.getClassifier() == null )
            {
                target.setClassifier( src );
                target.setLocation( "classifier", source.getLocation( "classifier" ) );
            }
        }
    }
