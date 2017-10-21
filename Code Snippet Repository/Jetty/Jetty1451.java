    @Test
    public void HttpMethodTest()
    {
        Assert.assertNull(HttpMethod.lookAheadGet(BufferUtil.toBuffer("Wibble ")));
        Assert.assertNull(HttpMethod.lookAheadGet(BufferUtil.toBuffer("GET")));
        Assert.assertNull(HttpMethod.lookAheadGet(BufferUtil.toBuffer("MO")));

        Assert.assertEquals(HttpMethod.GET, HttpMethod.lookAheadGet(BufferUtil.toBuffer("GET ")));
        Assert.assertEquals(HttpMethod.MOVE, HttpMethod.lookAheadGet(BufferUtil.toBuffer("MOVE ")));

        ByteBuffer b = BufferUtil.allocateDirect(128);
        BufferUtil.append(b, BufferUtil.toBuffer("GET"));
        Assert.assertNull(HttpMethod.lookAheadGet(b));

        BufferUtil.append(b, BufferUtil.toBuffer(" "));
        Assert.assertEquals(HttpMethod.GET, HttpMethod.lookAheadGet(b));
    }
