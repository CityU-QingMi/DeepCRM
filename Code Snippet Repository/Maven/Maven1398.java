    private static boolean hasErrors( Iterable<? extends ModelProblem> problems )
    {
        return any( transform( problems, new Function<ModelProblem, Severity>()
        {
            @Override
            public Severity apply( ModelProblem input )
            {
                return input.getSeverity();
            }
        } ), in( of( ERROR, FATAL ) ) );
    }
