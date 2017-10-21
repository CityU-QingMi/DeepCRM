    protected void mergeModelBase_DistributionManagement( ModelBase target, ModelBase source, boolean sourceDominant,
                                                          Map<Object, Object> context )
    {
        DistributionManagement src = source.getDistributionManagement();
        if ( src != null )
        {
            DistributionManagement tgt = target.getDistributionManagement();
            if ( tgt == null )
            {
                tgt = new DistributionManagement();
                target.setDistributionManagement( tgt );
            }
            mergeDistributionManagement( tgt, src, sourceDominant, context );
        }
    }
