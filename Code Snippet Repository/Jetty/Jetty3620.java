    @Test
    public void testGetterSafeFromNullPointerException()
    {
        Request request = new Request(null, null);

        assertNull(request.getAuthType());
        assertNull(request.getAuthentication());

        assertNull(request.getContentType());

        assertNull(request.getCookies());
        assertNull(request.getContext());
        assertNull(request.getContextPath());

        assertNull(request.getHttpFields());
        assertNull(request.getHttpURI());

        assertNotNull(request.getScheme());
        assertNotNull(request.getServerName());
        assertNotNull(request.getServerPort());

        assertNotNull(request.getAttributeNames());
        assertFalse(request.getAttributeNames().hasMoreElements());

        request.getParameterMap();
        assertNull(request.getQueryString());
        assertNotNull(request.getQueryParameters());
        assertEquals(0,request.getQueryParameters().size());
        assertNotNull(request.getParameterMap());
        assertEquals(0,request.getParameterMap().size());
    }
