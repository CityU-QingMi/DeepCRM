    @Test
    public void testNoFilter() throws Exception
    {
        List<Worker> workers = new ArrayList<>();
        for (int i = 0; i < NUM_CONNECTIONS; ++i)
        {
            workers.add(new Worker(i));
        }

        ExecutorService executor = Executors.newFixedThreadPool(NUM_CONNECTIONS);
        List<Future<Void>> futures = executor.invokeAll(workers, 10, TimeUnit.SECONDS);

        rethrowExceptions(futures);

        if (TestServlet.__maxSleepers <= MAX_QOS)
            LOG.warn("TEST WAS NOT PARALLEL ENOUGH!");
        else
            assertThat(TestServlet.__maxSleepers, Matchers.lessThanOrEqualTo(NUM_CONNECTIONS));
    }
