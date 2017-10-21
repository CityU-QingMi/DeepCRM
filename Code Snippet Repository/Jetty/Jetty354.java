    @Override
    public void onComplete(Result result)
    {
        Request request = result.getRequest();
        Response response = result.getResponse();
        if (result.isSucceeded())
            redirector.redirect(request, response, null);
        else
            redirector.fail(request, response, result.getFailure());
    }
