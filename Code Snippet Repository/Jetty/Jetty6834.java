    @Test
    public void testDeflateBasics() throws Exception
    {
        // Setup deflater basics
        Deflater compressor = new Deflater(Deflater.BEST_COMPRESSION, true);
        compressor.setStrategy(Deflater.DEFAULT_STRATEGY);

        // Text to compress
        String text = "info:";
        byte uncompressed[] = StringUtil.getUtf8Bytes(text);

        // Prime the compressor
        compressor.reset();
        compressor.setInput(uncompressed, 0, uncompressed.length);
        compressor.finish();

        // Perform compression
        ByteBuffer outbuf = ByteBuffer.allocate(64);
        BufferUtil.clearToFill(outbuf);

        while (!compressor.finished())
        {
            byte out[] = new byte[64];
            int len = compressor.deflate(out, 0, out.length, Deflater.SYNC_FLUSH);
            if (len > 0)
            {
                outbuf.put(out, 0, len);
            }
        }
        compressor.end();

        BufferUtil.flipToFlush(outbuf, 0);
        byte compressed[] = BufferUtil.toArray(outbuf);
        // Clear the BFINAL bit that has been set by the compressor.end() call.
        // In the real implementation we never end() the compressor.
        compressed[0] &= 0xFE;

        String actual = TypeUtil.toHexString(compressed);
        String expected = "CaCc4bCbB70200"; // what pywebsocket produces

        Assert.assertThat("Compressed data", actual, is(expected));
    }
