    public void testForwardedRequest() {
        String expectedString = "https://www.example.com/mywebapp/product/widget/promo.html";

        Mock mockHttpServletRequest = new Mock(HttpServletRequest.class);
        mockHttpServletRequest.expectAndReturn("getServerName", "www.example.com");
        mockHttpServletRequest.expectAndReturn("getScheme", "http");
        mockHttpServletRequest.expectAndReturn("getServerPort", 80);
        mockHttpServletRequest.expectAndReturn("getContextPath", "/mywebapp");
        mockHttpServletRequest.expectAndReturn("getAttribute", "javax.servlet.forward.request_uri", "/mywebapp/product/widget/");
        mockHttpServletRequest.expectAndReturn("getRequestURI", "/mywebapp/");

        Mock mockHttpServletResponse = new Mock(HttpServletResponse.class);
        mockHttpServletResponse.expectAndReturn("encodeURL", expectedString, expectedString);

        String actionName = "promo.html";
        Map params = new TreeMap();

        String urlString = urlHelper.buildUrl(actionName, (HttpServletRequest) mockHttpServletRequest.proxy(), (HttpServletResponse) mockHttpServletResponse.proxy(), params, "https", true, true);
        assertEquals(expectedString, urlString);
    }
