    @Test
    public void testUnknownIndex()
    {
        String encoded = "BE";
        ByteBuffer buffer = ByteBuffer.wrap(TypeUtil.fromHexString(encoded));

        HpackDecoder decoder = new HpackDecoder(128,8192);
        try
        {
            decoder.decode(buffer);
            Assert.fail();
        }
        catch (BadMessageException e)
        {
            assertThat(e.getCode(),equalTo(HttpStatus.BAD_REQUEST_400));
            assertThat(e.getReason(),Matchers.startsWith("Unknown index"));
        }
    
    }
