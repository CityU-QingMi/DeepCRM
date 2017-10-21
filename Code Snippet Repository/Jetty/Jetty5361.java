    @Test
    public void testRemoveWithMaxCapacityOne() throws Exception
    {
        BlockingArrayQueue<String> queue = new BlockingArrayQueue<>(1);

        String element = "A";
        Assert.assertTrue(queue.offer(element));
        Assert.assertTrue(queue.remove(element));

        Assert.assertTrue(queue.offer(element));
        Assert.assertEquals(element, queue.remove(0));
    }
