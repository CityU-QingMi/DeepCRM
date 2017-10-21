    @Test
    public void testSchemeAndOrAuthority() throws Exception
    {
        HttpURI uri = new HttpURI("/path/info");
        assertEquals("/path/info",uri.toString());
        
        uri.setAuthority("host",0);
        assertEquals("//host/path/info",uri.toString());
        
        uri.setAuthority("host",8888);
        assertEquals("//host:8888/path/info",uri.toString());
        
        uri.setScheme("http");
        assertEquals("http://host:8888/path/info",uri.toString());
        
        uri.setAuthority(null,0);
        assertEquals("http:/path/info",uri.toString());
        
    }
