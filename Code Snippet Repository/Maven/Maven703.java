    public static List<ExecutionPlanItem> createExecutionPlanItems( MavenProject mavenProject,
                                                                    List<MojoExecution> executions )
    {
        BuilderCommon.attachToThread( mavenProject );

        List<ExecutionPlanItem> result = new ArrayList<>();
        for ( MojoExecution mojoExecution : executions )
        {
            result.add( new ExecutionPlanItem( mojoExecution ) );
        }
        return result;
    }
