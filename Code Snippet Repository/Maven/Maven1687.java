    protected void mergeDistributionManagement_Repository( DistributionManagement target,
                                                           DistributionManagement source, boolean sourceDominant,
                                                           Map<Object, Object> context )
    {
        DeploymentRepository src = source.getRepository();
        if ( src != null )
        {
            DeploymentRepository tgt = target.getRepository();
            if ( tgt == null )
            {
                tgt = new DeploymentRepository();
                target.setRepository( tgt );
            }
            mergeDeploymentRepository( tgt, src, sourceDominant, context );
        }
    }
