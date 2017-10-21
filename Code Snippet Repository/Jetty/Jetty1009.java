    @Test
    public void testConcurrentWithSmallServerThreadPool() throws Exception
    {
        start(new LoadServlet());

        // Only one connection to the server.
        Session session = newClient(new Session.Listener.Adapter());

        int runs = 10;
        int iterations = 512;
        boolean result = IntStream.range(0, 16).parallel()
                .mapToObj(i -> IntStream.range(0, runs)
                        .mapToObj(j -> run(session, iterations))
                        .reduce(true, (acc, res) -> acc && res))
                .reduce(true, (acc, res) -> acc && res);

        Assert.assertTrue(result);
    }
