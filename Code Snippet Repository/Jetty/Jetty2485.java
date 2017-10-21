    @Test
    public void testWrongContentLength() throws Exception
    {
        
        startServer(new HttpServlet()
        {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
            {
                byte[] message = "tooshort".getBytes("ascii");
                resp.setContentType("text/plain;charset=ascii");
                resp.setHeader("Content-Length", Long.toString(message.length + 1));
                resp.getOutputStream().write(message);
            }
        });
        startProxy();
        startClient();

        try
        {
            ContentResponse response = client.newRequest("localhost", serverConnector.getLocalPort())
                .timeout(5, TimeUnit.SECONDS)
                .send();
            Assert.assertThat(response.getStatus(),Matchers.greaterThanOrEqualTo(500));   
        }
        catch(ExecutionException e)
        {     
            Assert.assertThat(e.getCause(),Matchers.instanceOf(IOException.class));
        }
    }
