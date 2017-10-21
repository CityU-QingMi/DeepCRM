    @Test
    public void setOnCompleteCallbackWithBlockingSend() throws Exception
    {
        final byte[] content = new byte[512];
        new Random().nextBytes(content);
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                response.getOutputStream().write(content);
            }
        });

        final Exchanger<Response> ex = new Exchanger<>();
        BufferingResponseListener listener = new BufferingResponseListener()
        {
            @Override
            public void onComplete(Result result)
            {
                try
                {
                    ex.exchange(result.getResponse());
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };


        client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .send(listener);

        Response response = ex.exchange(null);

        Assert.assertEquals(200, response.getStatus());
        Assert.assertArrayEquals(content, listener.getContent());

    }
