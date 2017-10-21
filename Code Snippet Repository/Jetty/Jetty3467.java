    @Test
    public void testSendArray() throws Exception
    {
        byte[] buffer=new byte[16*1024];
        Arrays.fill(buffer,0,4*1024,(byte)0x99);
        Arrays.fill(buffer,4*1024,12*1024,(byte)0x58);
        Arrays.fill(buffer,12*1024,16*1024,(byte)0x66);
        _handler._content=ByteBuffer.wrap(buffer);
        _handler._content.limit(12*1024);
        _handler._content.position(4*1024);
        String response=_connector.getResponse("GET / HTTP/1.0\nHost: localhost:80\n\n");
        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,containsString("\r\nXXXXXXXXXXXXXXXXXXXXXXXXXXX"));
        
        for (int i=0;i<4*1024;i++)
            assertEquals("i="+i,(byte)0x99,buffer[i]);
        for (int i=12*1024;i<16*1024;i++)
            assertEquals("i="+i,(byte)0x66,buffer[i]);
    }
