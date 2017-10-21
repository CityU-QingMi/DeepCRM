    @Test
    public void testConcurrent() throws Exception
    {
        Random random = new Random();
        ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(100);
        try
        {
            String reason = "THE_CAUSE";
            ConcurrentWriteFlusher[] flushers = new ConcurrentWriteFlusher[50000];
            FutureCallback[] futures = new FutureCallback[flushers.length];
            for (int i = 0; i < flushers.length; ++i)
            {
                int size = 5 + random.nextInt(15);
                ByteArrayEndPoint endPoint = new ByteArrayEndPoint(new byte[0], size);
                ConcurrentWriteFlusher flusher = new ConcurrentWriteFlusher(endPoint, scheduler, random);
                flushers[i] = flusher;
                FutureCallback callback = new FutureCallback();
                futures[i] = callback;
                scheduler.schedule(() -> flusher.onFail(new Throwable(reason)), random.nextInt(75) + 1, TimeUnit.MILLISECONDS);
                flusher.write(callback, BufferUtil.toBuffer("How Now Brown Cow."), BufferUtil.toBuffer(" The quick brown fox jumped over the lazy dog!"));
            }

            int completed = 0;
            int failed = 0;
            for (int i = 0; i < flushers.length; ++i)
            {
                try
                {
                    futures[i].get(15, TimeUnit.SECONDS);
                    Assert.assertEquals("How Now Brown Cow. The quick brown fox jumped over the lazy dog!", flushers[i].getContent());
                    completed++;
                }
                catch (ExecutionException x)
                {
                    Assert.assertEquals(reason, x.getCause().getMessage());
                    failed++;
                }
            }
            Assert.assertThat(completed, Matchers.greaterThan(0));
            Assert.assertThat(failed, Matchers.greaterThan(0));
            Assert.assertEquals(flushers.length, completed + failed);
        }
        finally
        {
            scheduler.shutdown();
        }
    }
