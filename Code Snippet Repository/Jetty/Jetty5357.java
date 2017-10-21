    @Test
    public void testGrow() throws Exception
    {
        BlockingArrayQueue<String> queue = new BlockingArrayQueue<>(3,2);
        Assert.assertEquals(3, queue.getCapacity());

        queue.add("a");
        queue.add("a");
        Assert.assertEquals(2, queue.size());
        Assert.assertEquals(3, queue.getCapacity());
        queue.add("a");
        queue.add("a");
        Assert.assertEquals(4, queue.size());
        Assert.assertEquals(5, queue.getCapacity());

        int s=5;
        int c=5;
        queue.add("a");

        for (int t=0;t<100;t++)
        {
            Assert.assertEquals(s, queue.size());
            Assert.assertEquals(c, queue.getCapacity());

            for (int i=queue.size();i-->0;)
                queue.poll();
            Assert.assertEquals(0, queue.size());
            Assert.assertEquals(c, queue.getCapacity());

            for (int i=queue.getCapacity();i-->0;)
                queue.add("a");
            queue.add("a");
            Assert.assertEquals(s + 1, queue.size());
            Assert.assertEquals(c + 2, queue.getCapacity());

            queue.poll();
            queue.add("a");
            queue.add("a");
            Assert.assertEquals(s + 2, queue.size());
            Assert.assertEquals(c + 2, queue.getCapacity());

            s+=2;
            c+=2;
        }
    }
