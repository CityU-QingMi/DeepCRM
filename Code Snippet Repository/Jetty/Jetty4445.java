    @Test
    public void testBlockingQosFilter() throws Exception
    {
        FilterHolder holder = new FilterHolder(QoSFilter2.class);
        holder.setAsyncSupported(true);
        holder.setInitParameter(QoSFilter.MAX_REQUESTS_INIT_PARAM, "" + MAX_QOS);
        _tester.getContext().getServletHandler().addFilterWithMapping(holder, "/*", EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC));

        List<Worker> workers = new ArrayList<>();
        for (int i = 0; i < NUM_CONNECTIONS; ++i)
        {
            workers.add(new Worker(i));
        }

        ExecutorService executor = Executors.newFixedThreadPool(NUM_CONNECTIONS);
        List<Future<Void>> futures = executor.invokeAll(workers, 10, TimeUnit.SECONDS);

        rethrowExceptions(futures);

        if (TestServlet.__maxSleepers < MAX_QOS)
            LOG.warn("TEST WAS NOT PARALLEL ENOUGH!");
        else
            Assert.assertEquals(TestServlet.__maxSleepers, MAX_QOS);
    }
