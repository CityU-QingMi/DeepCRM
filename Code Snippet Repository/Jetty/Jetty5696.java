    @Test
    public void testLong() throws Exception
    {
        String source = "abcXX";
        byte[] bytes = source.getBytes(StandardCharsets.UTF_8);
        bytes[3] = (byte)0xc0;
        bytes[4] = (byte)0x00;

        Utf8StringBuilder buffer = new Utf8StringBuilder();
        try
        {
            for (byte aByte : bytes) {
                buffer.append(aByte);
            }
            Assert.fail("Should have resulted in an Utf8Appendable.NotUtf8Exception");
        }
        catch (Utf8Appendable.NotUtf8Exception e)
        {
            // expected path
        }
        assertEquals("abc\ufffd",buffer.toString());
    }
