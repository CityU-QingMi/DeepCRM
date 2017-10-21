    protected void mergeModelBase_DependencyManagement( ModelBase target, ModelBase source, boolean sourceDominant,
                                                        Map<Object, Object> context )
    {
        DependencyManagement src = source.getDependencyManagement();
        if ( src != null )
        {
            DependencyManagement tgt = target.getDependencyManagement();
            if ( tgt == null )
            {
                tgt = new DependencyManagement();
                target.setDependencyManagement( tgt );
            }
            mergeDependencyManagement( tgt, src, sourceDominant, context );
        }
    }
