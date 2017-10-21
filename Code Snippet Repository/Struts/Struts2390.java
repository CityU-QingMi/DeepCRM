    public void testForceAddSchemeHostAndPort() throws Exception {
        String expectedUrl = "http://localhost/contextPath/path1/path2/myAction.action";

        Mock mockHttpServletRequest = new Mock(HttpServletRequest.class);
        mockHttpServletRequest.expectAndReturn("getScheme", "http");
        mockHttpServletRequest.expectAndReturn("getServerName", "localhost");
        mockHttpServletRequest.expectAndReturn("getContextPath", "/contextPath");
        mockHttpServletRequest.expectAndReturn("getServerPort", 80);

        Mock mockHttpServletResponse = new Mock(HttpServletResponse.class);
        mockHttpServletResponse.expectAndReturn("encodeURL", expectedUrl, expectedUrl);

        String result = urlHelper.buildUrl("/path1/path2/myAction.action", (HttpServletRequest) mockHttpServletRequest.proxy(), (HttpServletResponse) mockHttpServletResponse.proxy(), null, "http", true, true, true);
        assertEquals(expectedUrl, result);
        mockHttpServletRequest.verify();
    }
