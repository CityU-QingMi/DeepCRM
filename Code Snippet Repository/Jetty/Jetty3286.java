    @Test
    public void testLenientBehavior()
    {
        CookieCutter cutter = new CookieCutter();
        cutter.addCookieField(rawHeader);
        Cookie[] cookies = cutter.getCookies();
        if (expectedName==null)
            assertThat("Cookies.length", cookies.length, is(0));
        else
        {
            assertThat("Cookies.length", cookies.length, is(1));
            assertThat("Cookie.name", cookies[0].getName(), is(expectedName));
            assertThat("Cookie.value", cookies[0].getValue(), is(expectedValue));
        }
    }
