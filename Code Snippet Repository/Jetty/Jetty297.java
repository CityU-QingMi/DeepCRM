    private Request sendRedirect(final HttpRequest httpRequest, Response response, Response.CompleteListener listener, URI location, String method)
    {
        try
        {
            Request redirect = client.copyRequest(httpRequest, location);

            // Use given method
            redirect.method(method);

            redirect.onRequestBegin(new Request.BeginListener()
            {
                @Override
                public void onBegin(Request redirect)
                {
                    Throwable cause = httpRequest.getAbortCause();
                    if (cause != null)
                        redirect.abort(cause);
                }
            });

            redirect.send(listener);
            return redirect;
        }
        catch (Throwable x)
        {
            fail(httpRequest, response, x);
            return null;
        }
    }
