    @Test
    public void testIteratorWithModification() throws Exception
    {
        BlockingArrayQueue<String> queue = new BlockingArrayQueue<>(4,0,4);
        int count = queue.getMaxCapacity() - 1;
        for (int i = 0; i < count; ++i)
            queue.offer("" + i);

        int sum = 0;
        for (String element : queue)
        {
            ++sum;
            // Concurrent modification, must not change the iterator
            queue.remove(element);
        }

        Assert.assertEquals(count, sum);
        Assert.assertTrue(queue.isEmpty());
    }
