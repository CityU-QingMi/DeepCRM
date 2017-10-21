    @Test
    public void testOPTIONSWithRelativeRedirect() throws Exception
    {
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                if ("*".equals(target))
                {
                    // Be nasty and send a relative redirect.
                    // Code 303 will change the method to GET.
                    response.setStatus(HttpStatus.SEE_OTHER_303);
                    response.setHeader("Location", "/");
                }
            }
        });

        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(getScheme())
                .method(HttpMethod.OPTIONS)
                .path("*")
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
    }
