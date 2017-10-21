    @Test
    public void testNeverIndexSetCookie()
    {
        HpackEncoder encoder = new HpackEncoder(38*5);
        ByteBuffer buffer = BufferUtil.allocate(4096);
        
        HttpFields fields = new HttpFields();
        fields.put("set-cookie","some cookie value");

        // encode
        BufferUtil.clearToFill(buffer);
        encoder.encode(buffer,new MetaData(HttpVersion.HTTP_2,fields));
        BufferUtil.flipToFlush(buffer,0);
        
        // something was encoded!
        assertThat(buffer.remaining(),Matchers.greaterThan(0));
        
        // empty dynamic table
        Assert.assertEquals(0,encoder.getHpackContext().size());
        

        // encode again
        BufferUtil.clearToFill(buffer);
        encoder.encode(buffer,new MetaData(HttpVersion.HTTP_2,fields));
        BufferUtil.flipToFlush(buffer,0);
        
        // something was encoded!
        assertThat(buffer.remaining(),Matchers.greaterThan(0));
        
        // empty dynamic table
        Assert.assertEquals(0,encoder.getHpackContext().size());
        
    }
