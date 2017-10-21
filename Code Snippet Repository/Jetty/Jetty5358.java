    @Test
    @Slow
    public void testTake() throws Exception
    {
        final String[] data=new String[4];

        final BlockingArrayQueue<String> queue = new BlockingArrayQueue<>();

        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    data[0]=queue.take();
                    data[1]=queue.take();
                    Thread.sleep(1000);
                    data[2]=queue.take();
                    data[3]=queue.poll(100,TimeUnit.MILLISECONDS);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    Assert.fail();
                }
            }
        };

        thread.start();

        Thread.sleep(1000);

        queue.offer("zero");
        queue.offer("one");
        queue.offer("two");
        thread.join();

        Assert.assertEquals("zero", data[0]);
        Assert.assertEquals("one", data[1]);
        Assert.assertEquals("two", data[2]);
        Assert.assertEquals(null, data[3]);
    }
