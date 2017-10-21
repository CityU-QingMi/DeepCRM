    @Test
    public void testEncode8859Only() throws Exception
    {
        char bad[] = {(char)128,(char)0,(char)-1,' '-1};
        for (int i=0;i<bad.length;i++)
        {
            String s="bad '"+bad[i]+"'";

            try
            {
                Huffman.octetsNeeded(s);
                Assert.fail("i="+i);
            }
            catch(IllegalArgumentException e)
            {
            }

            try
            {
                Huffman.encode(BufferUtil.allocate(32),s);
                Assert.fail("i="+i);
            }
            catch(IllegalArgumentException e)
            {
            }
        }
    }
