    public Result redirect(Request request, Response response) throws InterruptedException, ExecutionException
    {
        final AtomicReference<Result> resultRef = new AtomicReference<>();
        final CountDownLatch latch = new CountDownLatch(1);
        Request redirect = redirect(request, response, new BufferingResponseListener()
        {
            @Override
            public void onComplete(Result result)
            {
                resultRef.set(new Result(result.getRequest(),
                        result.getRequestFailure(),
                        new HttpContentResponse(result.getResponse(), getContent(), getMediaType(), getEncoding()),
                        result.getResponseFailure()));
                latch.countDown();
            }
        });

        try
        {
            latch.await();
            Result result = resultRef.get();
            if (result.isFailed())
                throw new ExecutionException(result.getFailure());
            return result;
        }
        catch (InterruptedException x)
        {
            // If the application interrupts, we need to abort the redirect
            redirect.abort(x);
            throw x;
        }
    }
