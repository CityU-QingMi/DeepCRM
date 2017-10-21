    static Callback from(CompletableFuture<?> completable, InvocationType invocation)
    {
        if (completable instanceof Callback)
            return (Callback)completable;

        return new Callback()
        {
            @Override
            public void succeeded()
            {
                completable.complete(null);
            }

            @Override
            public void failed(Throwable x)
            {
                completable.completeExceptionally(x);
            }

            @Override
            public InvocationType getInvocationType()
            {
                return invocation;
            }
        };
    }
