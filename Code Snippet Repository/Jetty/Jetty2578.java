    @Test
    public void testPrefixPattern() throws IOException
    {
        String location = "http://api.company.com/";

        RedirectPatternRule rule = new RedirectPatternRule();
        rule.setPattern("/api/*");
        rule.setLocation(location);
        rule.setStatusCode(HttpStatus.MOVED_PERMANENTLY_301);

        rule.apply("/api/rest?foo=1",_request,_response);
        assertRedirectResponse(HttpStatus.MOVED_PERMANENTLY_301,location);
    }
