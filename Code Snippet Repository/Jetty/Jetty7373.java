    @Test
    public void testEmptyRequestTrailers() throws Exception
    {
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
                Assert.assertNull(trailers);
            }
        });

        HttpFields trailers = new HttpFields();
        HttpRequest request = (HttpRequest)client.newRequest(newURI());
        request = request.trailers(() -> trailers);
        ContentResponse response = request.timeout(5, TimeUnit.SECONDS).send();
        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
    }
