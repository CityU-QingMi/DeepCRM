    @Test
    public void testListIteratorWithWrappedHead() throws Exception
    {
        BlockingArrayQueue<String> queue = new BlockingArrayQueue<>(4,0,4);
        // This sequence of offers and polls wraps the head around the array
        queue.offer("0");
        queue.offer("1");
        queue.offer("2");
        queue.offer("3");
        queue.poll();
        queue.poll();

        String element1 = queue.get(0);
        String element2 = queue.get(1);

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
