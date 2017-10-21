    @Test
    public void testStreamWithContentThenNextThenNext()
    {
        final AtomicBoolean closed = new AtomicBoolean();
        ByteArrayInputStream stream = new ByteArrayInputStream(new byte[]{1})
        {
            @Override
            public void close() throws IOException
            {
                super.close();
                closed.compareAndSet(false, true);
            }
        };

        InputStreamContentProvider provider = new InputStreamContentProvider(stream);
        Iterator<ByteBuffer> iterator = provider.iterator();

        Assert.assertNotNull(iterator);

        ByteBuffer buffer = iterator.next();

        Assert.assertNotNull(buffer);

        try
        {
            iterator.next();
            Assert.fail();
        }
        catch (NoSuchElementException expected)
        {
        }

        Assert.assertFalse(iterator.hasNext());
        Assert.assertTrue(closed.get());
    }
