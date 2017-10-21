    @Test(timeout=5000)
    public void testBlockOnRead() throws Exception
    {
        try (MessageInputStream stream = new MessageInputStream())
        {
            final AtomicBoolean hadError = new AtomicBoolean(false);
            final CountDownLatch startLatch = new CountDownLatch(1);

            // This thread fills the stream (from the "worker" thread)
            // But slowly (intentionally).
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        startLatch.countDown();
                        boolean fin = false;
                        TimeUnit.MILLISECONDS.sleep(200);
                        stream.appendFrame(BufferUtil.toBuffer("Saved",StandardCharsets.UTF_8),fin);
                        TimeUnit.MILLISECONDS.sleep(200);
                        stream.appendFrame(BufferUtil.toBuffer(" by ",StandardCharsets.UTF_8),fin);
                        fin = true;
                        TimeUnit.MILLISECONDS.sleep(200);
                        stream.appendFrame(BufferUtil.toBuffer("Zero",StandardCharsets.UTF_8),fin);
                    }
                    catch (IOException | InterruptedException e)
                    {
                        hadError.set(true);
                        e.printStackTrace(System.err);
                    }
                }
            }).start();

            // wait for thread to start
            startLatch.await();
            
            // Read it from the stream.
            byte buf[] = new byte[32];
            int len = stream.read(buf);
            String message = new String(buf,0,len,StandardCharsets.UTF_8);

            // Test it
            Assert.assertThat("Error when appending",hadError.get(),is(false));
            Assert.assertThat("Message",message,is("Saved by Zero"));
        }
    }
