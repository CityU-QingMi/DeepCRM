    protected void mergePluginConfiguration_PluginManagement( PluginConfiguration target, PluginConfiguration source,
                                                              boolean sourceDominant, Map<Object, Object> context )
    {
        PluginManagement src = source.getPluginManagement();
        if ( src != null )
        {
            PluginManagement tgt = target.getPluginManagement();
            if ( tgt == null )
            {
                tgt = new PluginManagement();
                target.setPluginManagement( tgt );
            }
            mergePluginManagement( tgt, src, sourceDominant, context );
        }
    }
