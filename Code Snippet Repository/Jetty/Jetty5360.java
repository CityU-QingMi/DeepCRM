    @Test
    public void testRemoveObject() throws Exception
    {
        BlockingArrayQueue<String> queue = new BlockingArrayQueue<>(4,0,4);

        String element1 = "A";
        Assert.assertTrue(queue.offer(element1));
        Assert.assertTrue(queue.remove(element1));

        for (int i = 0; i < queue.getMaxCapacity() - 1; ++i)
        {
            queue.offer("" + i);
            queue.poll();
        }
        String element2 = "B";
        Assert.assertTrue(queue.offer(element2));
        Assert.assertTrue(queue.offer(element1));
        Assert.assertTrue(queue.remove(element1));

        Assert.assertFalse(queue.remove("NOT_PRESENT"));

        Assert.assertTrue(queue.remove(element2));
        Assert.assertFalse(queue.remove("NOT_PRESENT"));

        queue.clear();

        for (int i = 0; i < queue.getMaxCapacity(); ++i)
            queue.offer("" + i);

        Assert.assertTrue(queue.remove("" + (queue.getMaxCapacity() - 1)));
    }
