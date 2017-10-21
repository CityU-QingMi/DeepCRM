    @Test
    public void testWrap() throws Exception
    {
        BlockingArrayQueue<String> queue = new BlockingArrayQueue<>(3);

        Assert.assertEquals(0, queue.size());

        for (int i=0;i<queue.getMaxCapacity();i++)
        {
            queue.offer("one");
            Assert.assertEquals(1, queue.size());

            queue.offer("two");
            Assert.assertEquals(2, queue.size());

            queue.offer("three");
            Assert.assertEquals(3, queue.size());

            Assert.assertEquals("one", queue.get(0));
            Assert.assertEquals("two", queue.get(1));
            Assert.assertEquals("three", queue.get(2));

            Assert.assertEquals("[one, two, three]", queue.toString());

            Assert.assertEquals("one", queue.poll());
            Assert.assertEquals(2, queue.size());

            Assert.assertEquals("two", queue.poll());
            Assert.assertEquals(1, queue.size());

            Assert.assertEquals("three", queue.poll());
            Assert.assertEquals(0, queue.size());


            queue.offer("xxx");
            Assert.assertEquals(1, queue.size());
            Assert.assertEquals("xxx", queue.poll());
            Assert.assertEquals(0, queue.size());
        }
    }
