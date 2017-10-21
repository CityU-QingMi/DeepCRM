    @Test
    public void testMutableURI()
    {
        HttpURI uri = new HttpURI("/foo/bar");
        assertEquals("/foo/bar",uri.toString());
        assertEquals("/foo/bar",uri.getPath());
        assertEquals("/foo/bar",uri.getDecodedPath());

        uri.setScheme("http");
        assertEquals("http:/foo/bar",uri.toString());
        assertEquals("/foo/bar",uri.getPath());
        assertEquals("/foo/bar",uri.getDecodedPath());

        uri.setAuthority("host",0);
        assertEquals("http://host/foo/bar",uri.toString());
        assertEquals("/foo/bar",uri.getPath());
        assertEquals("/foo/bar",uri.getDecodedPath());

        uri.setAuthority("host",8888);
        assertEquals("http://host:8888/foo/bar",uri.toString());
        assertEquals("/foo/bar",uri.getPath());
        assertEquals("/foo/bar",uri.getDecodedPath());
        
        uri.setPathQuery("/f%30%30;p0/bar;p1;p2");
        assertEquals("http://host:8888/f%30%30;p0/bar;p1;p2",uri.toString());
        assertEquals("/f%30%30;p0/bar;p1;p2",uri.getPath());
        assertEquals("/f00/bar",uri.getDecodedPath());
        assertEquals("p2",uri.getParam());
        assertEquals(null,uri.getQuery());
        
        uri.setPathQuery("/f%30%30;p0/bar;p1;p2?name=value");
        assertEquals("http://host:8888/f%30%30;p0/bar;p1;p2?name=value",uri.toString());
        assertEquals("/f%30%30;p0/bar;p1;p2",uri.getPath());
        assertEquals("/f00/bar",uri.getDecodedPath());
        assertEquals("p2",uri.getParam());
        assertEquals("name=value",uri.getQuery());
        
        uri.setQuery("other=123456");
        assertEquals("http://host:8888/f%30%30;p0/bar;p1;p2?other=123456",uri.toString());
        assertEquals("/f%30%30;p0/bar;p1;p2",uri.getPath());
        assertEquals("/f00/bar",uri.getDecodedPath());
        assertEquals("p2",uri.getParam());
        assertEquals("other=123456",uri.getQuery());
    }
