    @Test
    public void testNotMatch() throws Exception
    {
        reset();
        _rule.setRegex("/my/dir/file/(.*)$");
        _rule.setName("cache-control");
        _rule.setValue("no-store");
        _rule.matchAndApply("/my/dir/file_not_match/",_request,_response);
        assertEquals(null,_response.getHeader("cache-control"));
    }
