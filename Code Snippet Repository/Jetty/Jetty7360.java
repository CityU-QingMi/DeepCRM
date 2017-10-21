    @Test
    public void testResponseWithContentCompleteListenerInvokedOnce() throws Exception
    {
        start(new EmptyServerHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                super.handle(target, baseRequest, request, response);
                response.getWriter().write("Jetty");
            }
        });

        AtomicInteger completes = new AtomicInteger();
        client.newRequest(newURI())
                .send(result -> completes.incrementAndGet());

        sleep(1000);

        Assert.assertEquals(1, completes.get());
    }
