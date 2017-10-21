    @Test
    public void testListIterator() throws Exception
    {
        BlockingArrayQueue<String> queue = new BlockingArrayQueue<>(4,0,4);
        String element1 = "A";
        String element2 = "B";
        queue.offer(element1);
        queue.offer(element2);

        ListIterator<String> iterator = queue.listIterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertFalse(iterator.hasPrevious());

        String element = iterator.next();
        Assert.assertEquals(element1, element);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertTrue(iterator.hasPrevious());

        element = iterator.next();
        Assert.assertEquals(element2, element);
        Assert.assertFalse(iterator.hasNext());
        Assert.assertTrue(iterator.hasPrevious());

        element = iterator.previous();
        Assert.assertEquals(element2, element);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertTrue(iterator.hasPrevious());

        element = iterator.previous();
        Assert.assertEquals(element1, element);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertFalse(iterator.hasPrevious());
    }
