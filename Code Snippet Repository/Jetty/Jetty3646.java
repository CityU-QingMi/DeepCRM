    @Test
    public void testAddCookie_JavaxServletHttp() throws Exception
    {
        Response response = getResponse();
    
        Cookie cookie = new Cookie("foo", URLEncoder.encode("bar;baz", UTF_8.toString()));
        cookie.setPath("/secure");
    
        response.addCookie(cookie);
    
        String set = response.getHttpFields().get("Set-Cookie");
    
        assertEquals("foo=bar%3Bbaz;Path=/secure", set);
    }
