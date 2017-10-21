    public static <T> Result<Iterable<T>> newResultSet( Iterable<? extends Result<? extends T>> results )
    {
        final boolean hasErrors = any( transform( results, new Function<Result<?>, Boolean>()
        {
            @Override
            public Boolean apply( Result<?> input )
            {
                return input.hasErrors();
            }
        } ), Predicates.equalTo( true ) );
        final Iterable<T> models = transform( results, new Function<Result<? extends T>, T>()
        {
            @Override
            public T apply( Result<? extends T> input )
            {
                return input.get();
            }
        } );
        final Iterable<ModelProblem> problems = concat( transform( results, GET_PROBLEMS ) );
        return new Result<>( hasErrors, models, problems );
    }
