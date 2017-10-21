    @Test
    public void testRequestWithoutResponseContent() throws Exception
    {
        final int status = HttpStatus.NO_CONTENT_204;
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                response.setStatus(status);
            }
        });

        ContentResponse response = client.newRequest(newURI())
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(status, response.getStatus());
        Assert.assertEquals(0, response.getContent().length);
    }
