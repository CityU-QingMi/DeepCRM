    @Test
    public void testLimit() throws Exception
    {
        BlockingArrayQueue<String> queue = new BlockingArrayQueue<>(1,0,1);

        String element = "0";
        Assert.assertTrue(queue.add(element));
        Assert.assertFalse(queue.offer("1"));

        Assert.assertEquals(element, queue.poll());
        Assert.assertTrue(queue.add(element));
    }
