    @Test
    public void testCookiesWithReset() throws Exception
    {
        Response response = getResponse();

        Cookie cookie=new Cookie("name","value");
        cookie.setDomain("domain");
        cookie.setPath("/path");
        cookie.setSecure(true);
        cookie.setComment("comment__HTTP_ONLY__");
        response.addCookie(cookie);

        Cookie cookie2=new Cookie("name2", "value2");
        cookie2.setDomain("domain");
        cookie2.setPath("/path");
        response.addCookie(cookie2);

        //keep the cookies
        response.reset(true);

        Enumeration<String> set = response.getHttpFields().getValues("Set-Cookie");

        assertNotNull(set);
        ArrayList<String> list = Collections.list(set);
        assertEquals(2, list.size());
        assertTrue(list.contains("name=value;Path=/path;Domain=domain;Secure;HttpOnly"));
        assertTrue(list.contains("name2=value2;Path=/path;Domain=domain"));

        //get rid of the cookies
        response.reset();

        set = response.getHttpFields().getValues("Set-Cookie");
        assertFalse(set.hasMoreElements());
    }
