    @Test
    public void testLocale() throws Exception
    {
        _handler._checker = new RequestTester()
        {
            @Override
            public boolean check(HttpServletRequest request,HttpServletResponse response) throws IOException
            {
                assertThat(request.getLocale().getLanguage(),is("da"));
                Enumeration<Locale> locales = request.getLocales();
                Locale locale=locales.nextElement();
                assertThat(locale.getLanguage(),is("da"));
                assertThat(locale.getCountry(),is(""));
                locale=locales.nextElement();
                assertThat(locale.getLanguage(),is("en"));
                assertThat(locale.getCountry(),is("AU"));
                locale=locales.nextElement();
                assertThat(locale.getLanguage(),is("en"));
                assertThat(locale.getCountry(),is("GB"));
                locale=locales.nextElement();
                assertThat(locale.getLanguage(),is("en"));
                assertThat(locale.getCountry(),is(""));
                assertFalse(locales.hasMoreElements());
                return true;
            }
        };

        String request="GET / HTTP/1.1\r\n"+
            "Host: whatever\r\n"+
            "Connection: close\r\n"+
            "Accept-Language: da, en-gb;q=0.8, en;q=0.7\r\n"+
            "Accept-Language: XX;q=0, en-au;q=0.9\r\n"+
            "\r\n";
        String response = _connector.getResponse(request);
        assertThat(response, containsString(" 200 OK"));
    }
