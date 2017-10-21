    @Test
    public void evictReferencedFieldTest()
    {
        HpackEncoder encoder = new HpackEncoder(200,200);
        HpackDecoder decoder = new HpackDecoder(200,1024);
        ByteBuffer buffer = BufferUtil.allocate(16*1024);
        
        HttpFields fields0 = new HttpFields();
        fields0.add("123456789012345678901234567890123456788901234567890","value");
        fields0.add("foo","abcdeffhijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQR");
        MetaData original0= new MetaData(HttpVersion.HTTP_2,fields0);

        BufferUtil.clearToFill(buffer);
        encoder.encode(buffer,original0);
        BufferUtil.flipToFlush(buffer,0);
        MetaData decoded0 = (MetaData)decoder.decode(buffer);

        assertEquals(2,encoder.getHpackContext().size());
        assertEquals(2,decoder.getHpackContext().size());
        assertEquals("123456789012345678901234567890123456788901234567890",encoder.getHpackContext().get(HpackContext.STATIC_TABLE.length+1).getHttpField().getName());
        assertEquals("foo",encoder.getHpackContext().get(HpackContext.STATIC_TABLE.length+0).getHttpField().getName());
        
        assertMetadataSame(original0,decoded0);
               
        HttpFields fields1 = new HttpFields();
        fields1.add("123456789012345678901234567890123456788901234567890","other_value");
        fields1.add("x","y");
        MetaData original1 = new MetaData(HttpVersion.HTTP_2,fields1);

        BufferUtil.clearToFill(buffer);
        encoder.encode(buffer,original1);
        BufferUtil.flipToFlush(buffer,0);
        MetaData decoded1 = (MetaData)decoder.decode(buffer);
        assertMetadataSame(original1,decoded1);
        
        assertEquals(2,encoder.getHpackContext().size());
        assertEquals(2,decoder.getHpackContext().size());
        assertEquals("x",encoder.getHpackContext().get(HpackContext.STATIC_TABLE.length+0).getHttpField().getName());
        assertEquals("foo",encoder.getHpackContext().get(HpackContext.STATIC_TABLE.length+1).getHttpField().getName());        
    }
