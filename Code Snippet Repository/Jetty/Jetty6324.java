    @SuppressWarnings("")
    @Test
    public void testMemoryUsage() throws Exception
    {
        int sessionCount = 1000;
        Session[] sessions = new Session[sessionCount];

        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

        System.gc();
        MemoryUsage heapBefore = memoryMXBean.getHeapMemoryUsage();
        MemoryUsage nonHeapBefore = memoryMXBean.getNonHeapMemoryUsage();

        URI uri = URI.create("ws://localhost:" + connector.getLocalPort());
        final CountDownLatch latch = new CountDownLatch(sessionCount);
        for (int i = 0; i < sessionCount; ++i)
        {
            sessions[i] = client.connectToServer(new EndpointAdapter()
            {
                @Override
                public void onMessage(String message)
                {
                    latch.countDown();
                }
            }, uri);
        }
        for (int i = 0; i < sessionCount; ++i)
        {
            sessions[i].getBasicRemote().sendText("OK");
        }
        latch.await(5 * sessionCount, TimeUnit.MILLISECONDS);

        System.gc();
        MemoryUsage heapAfter = memoryMXBean.getHeapMemoryUsage();
        MemoryUsage nonHeapAfter = memoryMXBean.getNonHeapMemoryUsage();

        long heapUsed = heapAfter.getUsed() - heapBefore.getUsed();
        long nonHeapUsed = nonHeapAfter.getUsed() - nonHeapBefore.getUsed();

        System.out.println("heapUsed = " + heapUsed);
        System.out.println("nonHeapUsed = " + nonHeapUsed);
//        new CountDownLatch(1).await();

        // Assume no more than 25 KiB per session pair (client and server).
        long expected = 25 * 1024 * sessionCount;
        Assert.assertThat("heap used", heapUsed,lessThan(expected));
    }
