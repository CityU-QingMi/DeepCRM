    @Test
    public void testHEADWithAcceptHeaderAndSendError() throws Exception
    {
        int status = HttpStatus.BAD_REQUEST_400;
        start(new HttpServlet()
        {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
            {
                resp.sendError(status);
            }
        });

        ContentResponse response = client.newRequest(newURI())
                .method(HttpMethod.HEAD)
                .path(servletPath)
                .header(HttpHeader.ACCEPT, "*/*")
                .send();

        Assert.assertEquals(status, response.getStatus());
        Assert.assertEquals(0, response.getContent().length);
    }
