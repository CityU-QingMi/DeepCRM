    public void testCacheInstanceWithManyThreads() throws BrokenBarrierException, InterruptedException {
        //start a bunch of thread at the same time using CyclicBarrier and hit the cache
        //then wait for all the threads to end and check that they all got a reference to the same object
        //and the cache size should be 1

        DummyServletCache cache = new DummyServletCache();
        int numThreads = 70;

        CyclicBarrier startBarrier = new CyclicBarrier(numThreads + 1);
        CyclicBarrier endBarrier = new CyclicBarrier(numThreads + 1);

        List<ServletGetRunnable> runnables = new ArrayList<ServletGetRunnable>(numThreads);

        //create the threads
        for (int i = 0; i < numThreads; i++) {
            ServletGetRunnable runnable = new ServletGetRunnable(cache, startBarrier, endBarrier, ActionContext.getContext());
            Thread thread = new Thread(runnable);
            runnables.add(runnable);
            thread.start();
        }

        startBarrier.await();
        endBarrier.await();
        Object servlet = cache.get("org/apache/struts2/simple0.jsp");
        assertEquals(1, cache.size());

        for (ServletGetRunnable runnable : runnables) {
            assertSame(servlet, runnable.getObject());
        }
    }
