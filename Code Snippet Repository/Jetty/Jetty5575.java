    @Test
    public void testWatchFile() throws Exception
    {
        Path dir = testdir.getEmptyPathDir();

        // Files we are interested in
        Files.createDirectories(dir.resolve("bar/WEB-INF"));
        Files.createFile(dir.resolve("bar/WEB-INF/web.xml"));

        PathWatcher pathWatcher = new PathWatcher();
        pathWatcher.setUpdateQuietTime(QUIET_TIME,TimeUnit.MILLISECONDS);

        // Add listener
        PathWatchEventCapture capture = new PathWatchEventCapture(dir);
        pathWatcher.addListener(capture);

        // Add test configuration
        pathWatcher.watch(dir.resolve("bar/WEB-INF/web.xml"));
        pathWatcher.setNotifyExistingOnStart(false);

        try
        {
            pathWatcher.start();
            Thread.sleep(WAIT_TIME);
            assertThat(capture.events.size(),is(0));

            Files.createFile(dir.resolve("bar/index.htnl"));
            Files.createFile(dir.resolve("bar/WEB-INF/other.xml"));
            Files.createDirectories(dir.resolve("bar/WEB-INF/lib"));

            Thread.sleep(WAIT_TIME);
            assertThat(capture.events.size(),is(0));

            capture.setFinishTrigger(1);
            updateFile(dir.resolve("bar/WEB-INF/web.xml"),"Update web.xml");
            assertTrue(capture.finishedLatch.await(LONG_TIME,TimeUnit.MILLISECONDS));

            Map<String, PathWatchEventType[]> expected = new HashMap<>();

            expected.put("bar/WEB-INF/web.xml",new PathWatchEventType[] { MODIFIED });

            capture.assertEvents(expected);
            TimeUnit.MILLISECONDS.sleep(WAIT_TIME);
            capture.assertEvents(expected);
        }
        finally
        {
            pathWatcher.stop();
        }
    }
