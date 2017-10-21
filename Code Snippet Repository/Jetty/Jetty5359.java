    @Test
    public void testRemoveObjectWithWrappedTail() throws Exception
    {
        BlockingArrayQueue<String> queue = new BlockingArrayQueue<>(6);
        // Wrap the tail
        for (int i = 0; i < queue.getMaxCapacity(); ++i)
            queue.offer("" + i);
        // Advance the head
        queue.poll();
        // Remove from the middle
        Assert.assertTrue(queue.remove("2"));

        // Advance the tail
        Assert.assertTrue(queue.offer("A"));
        Assert.assertTrue(queue.offer("B"));
        queue.poll();
        // Remove from the middle
        Assert.assertTrue(queue.remove("3"));
    }
