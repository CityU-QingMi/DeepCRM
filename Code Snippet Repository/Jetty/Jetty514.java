    private void testMethodRedirect(final HttpMethod requestMethod, final HttpMethod redirectMethod, int redirectCode) throws Exception
    {
        start(new RedirectHandler());

        final AtomicInteger passes = new AtomicInteger();
        client.getRequestListeners().add(new org.eclipse.jetty.client.api.Request.Listener.Adapter()
        {
            @Override
            public void onBegin(org.eclipse.jetty.client.api.Request request)
            {
                int pass = passes.incrementAndGet();
                if (pass == 1)
                {
                    if (!requestMethod.is(request.getMethod()))
                        request.abort(new Exception());
                }
                else if (pass == 2)
                {
                    if (!redirectMethod.is(request.getMethod()))
                        request.abort(new Exception());
                }
                else
                {
                    request.abort(new Exception());
                }
            }
        });

        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .method(requestMethod)
                .path("/" + redirectCode + "/localhost/done")
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(200, response.getStatus());
    }
