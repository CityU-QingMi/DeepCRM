    @Test
    public void testParams() throws Exception
    {
        HttpURI uri = new HttpURI("/foo/bar");
        assertEquals("/foo/bar",uri.getPath());
        assertEquals("/foo/bar",uri.getDecodedPath());
        assertEquals(null,uri.getParam());
        
        uri = new HttpURI("/foo/bar;jsessionid=12345");
        assertEquals("/foo/bar;jsessionid=12345",uri.getPath());
        assertEquals("/foo/bar",uri.getDecodedPath());
        assertEquals("jsessionid=12345",uri.getParam());
        
        uri = new HttpURI("/foo;abc=123/bar;jsessionid=12345");
        assertEquals("/foo;abc=123/bar;jsessionid=12345",uri.getPath());
        assertEquals("/foo/bar",uri.getDecodedPath());
        assertEquals("jsessionid=12345",uri.getParam());
        
        uri = new HttpURI("/foo;abc=123/bar;jsessionid=12345?name=value");
        assertEquals("/foo;abc=123/bar;jsessionid=12345",uri.getPath());
        assertEquals("/foo/bar",uri.getDecodedPath());
        assertEquals("jsessionid=12345",uri.getParam());
        
        uri = new HttpURI("/foo;abc=123/bar;jsessionid=12345#target");
        assertEquals("/foo;abc=123/bar;jsessionid=12345",uri.getPath());
        assertEquals("/foo/bar",uri.getDecodedPath());
        assertEquals("jsessionid=12345",uri.getParam());
    }
