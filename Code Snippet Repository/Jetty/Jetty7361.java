    private void testHEAD(String path, int status) throws Exception
    {
        byte[] data = new byte[1024];
        new Random().nextBytes(data);
        start(new HttpServlet()
        {
            @Override
            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                response.getOutputStream().write(data);
            }
        });

        ContentResponse response = client.newRequest(newURI())
                .method(HttpMethod.HEAD)
                .path(path)
                .send();

        Assert.assertEquals(status, response.getStatus());
        Assert.assertEquals(0, response.getContent().length);
    }
