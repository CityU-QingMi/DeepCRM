    @Test
    public  void testGenerateParseLargeContent()
    {
        ByteBuffer content = ByteBuffer.wrap(largeContent);
        List<DataFrame> frames = testGenerateParse(content);
        Assert.assertEquals(8, frames.size());
        ByteBuffer aggregate = ByteBuffer.allocate(content.remaining());
        for (int i = 1; i <= frames.size(); ++i)
        {
            DataFrame frame = frames.get(i - 1);
            Assert.assertTrue(frame.getStreamId() != 0);
            Assert.assertEquals(i == frames.size(), frame.isEndStream());
            aggregate.put(frame.getData());
        }
        aggregate.flip();
        Assert.assertEquals(content, aggregate);
    }
