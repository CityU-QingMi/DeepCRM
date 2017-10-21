    @Test
    public void testAbortedWhileQueued() throws Exception
    {
        long sleep = 1000;
        start(1, new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                sleep(sleep);
            }
        });
        client.setMaxConnectionsPerDestination(1);

        primeConnection();

        // Send a request that is aborted while queued.
        client.newRequest("localhost", connector.getLocalPort())
                .path("/aborted")
                .onRequestQueued(request -> request.abort(new Exception()))
                .send(null);

        // Must be able to send another request.
        ContentResponse response = client.newRequest("localhost", connector.getLocalPort()).path("/check").send();

        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
    }
