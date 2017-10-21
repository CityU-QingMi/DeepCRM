    @Test
    public void testDecodeTrailingFF() throws Exception
    {
        for (String[] test:tests)
        {
            byte[] encoded=TypeUtil.fromHexString(test[1]+"FF");
            String decoded=Huffman.decode(ByteBuffer.wrap(encoded));
            Assert.assertEquals(test[0],test[2],decoded);
        }
    }
