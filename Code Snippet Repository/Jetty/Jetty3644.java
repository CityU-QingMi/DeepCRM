    @Test
    public void testAddCookieComplianceRFC2965() throws Exception
    {
        Response response = getResponse();
        response.getHttpChannel().getHttpConfiguration().setCookieCompliance(CookieCompliance.RFC2965);

        Cookie cookie = new Cookie("name", "value");
        cookie.setDomain("domain");
        cookie.setPath("/path");
        cookie.setSecure(true);
        cookie.setComment("comment__HTTP_ONLY__");

        response.addCookie(cookie);

        String set = response.getHttpFields().get("Set-Cookie");

        assertEquals("name=value;Version=1;Path=/path;Domain=domain;Secure;HttpOnly;Comment=comment", set);
    }
