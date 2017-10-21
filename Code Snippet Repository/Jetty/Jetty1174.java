    @Test
    public void testDecode() throws Exception
    {
        for (String[] test:tests)
        {
            byte[] encoded=TypeUtil.fromHexString(test[1]);
            String decoded=Huffman.decode(ByteBuffer.wrap(encoded));
            Assert.assertEquals(test[0],test[2],decoded);
        }
    }
