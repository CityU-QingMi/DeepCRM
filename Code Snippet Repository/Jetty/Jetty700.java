    @Test
    public void testCloseNextHasNextReturnsFalse() throws Exception
    {
        DeferredContentProvider provider = new DeferredContentProvider();
        Iterator<ByteBuffer> iterator = provider.iterator();

        provider.close();

        Assert.assertFalse(iterator.hasNext());

        try
        {
            iterator.next();
            Assert.fail();
        }
        catch (NoSuchElementException x)
        {
            // Expected
        }

        Assert.assertFalse(iterator.hasNext());
    }
