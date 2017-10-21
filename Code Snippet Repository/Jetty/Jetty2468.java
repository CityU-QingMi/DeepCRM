    @Test
    public void test() throws Exception
    {
        startServer(new HttpServlet()
        {
            @Override
            protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
            {
                if (req.getHeader("Via") != null)
                    resp.addHeader(PROXIED_HEADER, "true");
                IO.copy(req.getInputStream(), resp.getOutputStream());
            }
        });
        startProxy();
        startClient();

        // Number of clients to simulate
        int clientCount = Runtime.getRuntime().availableProcessors();

        // Latch for number of clients still active (used to terminate test)
        final CountDownLatch activeClientLatch = new CountDownLatch(clientCount);

        // Atomic Boolean to track that its OK to still continue looping.
        // When this goes false, that means one of the client threads has
        // encountered an error condition, and should allow all remaining
        // client threads to finish cleanly.
        final AtomicBoolean success = new AtomicBoolean(true);

        int iterations = 1000;

        // Start clients
        for (int i = 0; i < clientCount; i++)
        {
            ClientLoop r = new ClientLoop(activeClientLatch, success, client, "localhost", serverConnector.getLocalPort(), iterations);
            String name = "client-" + i;
            Thread thread = new Thread(r, name);
            thread.start();
        }

        Assert.assertTrue(activeClientLatch.await(Math.max(clientCount * iterations * 10, 5000), TimeUnit.MILLISECONDS));
        Assert.assertTrue(success.get());
    }
