    private void testRequestTrailers(byte[] content) throws Exception
    {
        String trailerName = "Trailer";
        String trailerValue = "value";
        start(new AbstractHandler.ErrorDispatchHandler()
        {
            @Override
            protected void doNonErrorHandle(String target, Request jettyRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                jettyRequest.setHandled(true);

                // Read the content first.
                ServletInputStream input = jettyRequest.getInputStream();
                while (true)
                {
                    int read = input.read();
                    if (read < 0)
                        break;
                }

                // Now the trailers can be accessed.
                HttpFields trailers = jettyRequest.getTrailers();
                Assert.assertNotNull(trailers);
                Assert.assertEquals(trailerValue, trailers.get(trailerName));
            }
        });

        HttpFields trailers = new HttpFields();
        trailers.put(trailerName, trailerValue);

        HttpRequest request = (HttpRequest)client.newRequest(newURI());
        request = request.trailers(() -> trailers);
        if (content != null)
            request.method(HttpMethod.POST).content(new BytesContentProvider(content));
        ContentResponse response = request.timeout(5, TimeUnit.SECONDS).send();
        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
    }
