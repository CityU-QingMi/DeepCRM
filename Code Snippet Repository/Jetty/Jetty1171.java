    @Test
    public void encodeDecodeTooLargeTest()
    {
        HpackEncoder encoder = new HpackEncoder();
        HpackDecoder decoder = new HpackDecoder(4096,164);
        ByteBuffer buffer = BufferUtil.allocate(16*1024);
        
        HttpFields fields0 = new HttpFields();
        fields0.add("1234567890","1234567890123456789012345678901234567890");
        fields0.add("Cookie","abcdeffhijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQR");
        MetaData original0= new MetaData(HttpVersion.HTTP_2,fields0);
        
        BufferUtil.clearToFill(buffer);
        encoder.encode(buffer,original0);
        BufferUtil.flipToFlush(buffer,0);
        MetaData decoded0 = (MetaData)decoder.decode(buffer);

        assertMetadataSame(original0,decoded0);
               
        HttpFields fields1 = new HttpFields();
        fields1.add("1234567890","1234567890123456789012345678901234567890");
        fields1.add("Cookie","abcdeffhijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQR");
        fields1.add("x","y");
        MetaData original1 = new MetaData(HttpVersion.HTTP_2,fields1);

        BufferUtil.clearToFill(buffer);
        encoder.encode(buffer,original1);
        BufferUtil.flipToFlush(buffer,0);
        try
        {
            decoder.decode(buffer);
            Assert.fail();
        }
        catch(BadMessageException e)
        {
            assertEquals(HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE_431,e.getCode());
        }
    }
