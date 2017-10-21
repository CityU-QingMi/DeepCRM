    @Test
    public void testRedirectWithCorruptedBody() throws Exception
    {
        byte[] bytes = "ok".getBytes(StandardCharsets.UTF_8);
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                if (target.startsWith("/redirect"))
                {
                    response.setStatus(HttpStatus.SEE_OTHER_303);
                    response.setHeader(HttpHeader.LOCATION.asString(), scheme + "://localhost:" + connector.getLocalPort() + "/ok");
                    // Say that we send gzipped content, but actually don't.
                    response.setHeader(HttpHeader.CONTENT_ENCODING.asString(), "gzip");
                    response.getOutputStream().write("redirect".getBytes(StandardCharsets.UTF_8));
                }
                else
                {
                    response.setStatus(HttpStatus.OK_200);
                    response.getOutputStream().write(bytes);
                }
            }
        });

        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .path("/redirect")
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(200, response.getStatus());
        Assert.assertArrayEquals(bytes, response.getContent());
    }
