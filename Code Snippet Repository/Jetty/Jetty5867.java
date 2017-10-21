    @Test
    @Slow
    @Ignore
    public void testBenchmark() throws Exception
    {
        schedule(2000,10000,2000,50);
        PlatformMonitor benchmark = new PlatformMonitor();
        PlatformMonitor.Start start = benchmark.start();
        System.err.println(start);
        System.err.println(_scheduler);
        schedule(2000,30000,2000,50);
        PlatformMonitor.Stop stop = benchmark.stop();
        System.err.println(stop);
    }
