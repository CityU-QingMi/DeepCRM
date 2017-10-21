    @Test
    public void testEmptyResponseTrailers() throws Exception
    {
        start(new AbstractHandler.ErrorDispatchHandler()
        {
            @Override
            protected void doNonErrorHandle(String target, Request jettyRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                jettyRequest.setHandled(true);

                HttpFields trailers = new HttpFields();

                Response jettyResponse = (Response)response;
                jettyResponse.setTrailers(() -> trailers);
            }
        });

        AtomicReference<Throwable> failure = new AtomicReference<>(new Throwable("no_success"));
        ContentResponse response = client.newRequest(newURI())
                .onResponseSuccess(r ->
                {
                    try
                    {
                        HttpResponse httpResponse = (HttpResponse)r;
                        HttpFields trailers = httpResponse.getTrailers();
                        Assert.assertNull(trailers);
                        failure.set(null);
                    }
                    catch (Throwable x)
                    {
                        failure.set(x);
                    }
                })
                .timeout(5, TimeUnit.SECONDS)
                .send();
        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
        Assert.assertNull(failure.get());
    }
