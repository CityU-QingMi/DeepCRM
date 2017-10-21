    private void testResponseTrailers(byte[] content) throws Exception
    {
        String trailerName = "Trailer";
        String trailerValue = "value";
        start(new AbstractHandler.ErrorDispatchHandler()
        {
            @Override
            protected void doNonErrorHandle(String target, Request jettyRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                jettyRequest.setHandled(true);

                HttpFields trailers = new HttpFields();
                trailers.put(trailerName, trailerValue);

                Response jettyResponse = (Response)response;
                jettyResponse.setTrailers(() -> trailers);
                if (content != null)
                    response.getOutputStream().write(content);
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
                        Assert.assertNotNull(trailers);
                        Assert.assertEquals(trailerValue, trailers.get(trailerName));
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
