    @Test
    public void testHeaderValue() throws Exception
    {
        setRequestHeader("Front-End-Https", "on");
        _rule.setHeader("Front-End-Https");
        _rule.setHeaderValue("on");
        _rule.setScheme("https");
        _rule.matchAndApply("/",_request,_response);
        assertEquals("https",_request.getScheme());

        _request.setScheme("other");
        // header value doesn't match rule's value
        setRequestHeader("Front-End-Https", "off");
        _rule.matchAndApply("/",_request,_response);
        assertEquals("other",_request.getScheme());

        _request.setScheme(null);
        // header value can be any value
        setRequestHeader("Front-End-Https", "any");
        _rule.setHeaderValue(null);
        _rule.matchAndApply("/",_request,_response);
        assertEquals("https",_request.getScheme());
    }
