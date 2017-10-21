    @Test
    public void testRemove() throws Exception
    {
        BlockingArrayQueue<String> queue = new BlockingArrayQueue<>(3,3);

        queue.add("0");
        queue.add("x");

        for (int i=1;i<100;i++)
        {
            queue.add(""+i);
            queue.add("x");
            queue.remove(queue.size()-3);
            queue.set(queue.size()-3,queue.get(queue.size()-3)+"!");
        }

        for (int i=0;i<99;i++)
            Assert.assertEquals(i + "!", queue.get(i));
    }
