    private void assertCookies(String[] cookie,boolean setExpected) throws IOException
    {
            // set cookie pattern
            CookiePatternRule rule = new CookiePatternRule();
            rule.setPattern("*");
            rule.setName(cookie[0]);
            rule.setValue(cookie[1]);

            // System.out.println(rule.toString());

            // apply cookie pattern
            rule.apply(_request.getRequestURI(), _request, _response);

            // verify
            HttpFields httpFields = _response.getHttpFields();
            Enumeration<String> e = httpFields.getValues(HttpHeader.SET_COOKIE.asString());
            boolean set = false;
            while (e.hasMoreElements())
            {
                String[] result = (e.nextElement()).split("=");
                assertEquals(cookie[0], result[0]);
                assertEquals(cookie[1], result[1]);
                set=true;
            }
            
            assertEquals(setExpected,set);
    }
