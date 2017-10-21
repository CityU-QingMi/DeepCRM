    public Set<MojoDescriptor> getNonThreadSafeMojos()
    {
        Set<MojoDescriptor> mojos = new HashSet<>();
        for ( ExecutionPlanItem executionPlanItem : planItem )
        {
            final MojoExecution mojoExecution = executionPlanItem.getMojoExecution();
            if ( !mojoExecution.getMojoDescriptor().isThreadSafe() )
            {
                mojos.add( mojoExecution.getMojoDescriptor() );
            }
        }
        return mojos;
    }
