    @Test
    public void testStreamWithExceptionThenNext()
    {
        final AtomicBoolean closed = new AtomicBoolean();
        InputStream stream = new InputStream()
        {
            @Override
            public int read() throws IOException
            {
                throw new IOException();
            }

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
