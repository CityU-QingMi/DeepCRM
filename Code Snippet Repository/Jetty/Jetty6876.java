    @Test(timeout=10000)
    public void testBlockOnReadInitial() throws IOException
    {
        try (MessageInputStream stream = new MessageInputStream())
        {
            final AtomicBoolean hadError = new AtomicBoolean(false);

            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        boolean fin = true;
                        // wait for a little bit before populating buffers
                        TimeUnit.MILLISECONDS.sleep(400);
                        stream.appendFrame(BufferUtil.toBuffer("I will conquer",StandardCharsets.UTF_8),fin);
                    }
                    catch (IOException | InterruptedException e)
                    {
                        hadError.set(true);
                        e.printStackTrace(System.err);
                    }
                }
            }).start();

            // Read byte from stream.
            int b = stream.read();
            // Should be a byte, blocking till byte received.

            // Test it
            Assert.assertThat("Error when appending",hadError.get(),is(false));
            Assert.assertThat("Initial byte",b,is((int)'I'));
        }
    }
