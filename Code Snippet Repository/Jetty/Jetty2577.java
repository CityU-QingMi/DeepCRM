    @Test
    public void testGlobPattern() throws IOException
    {
        String location = "http://eclipse.com";

        RedirectPatternRule rule = new RedirectPatternRule();
        rule.setPattern("*");
        rule.setLocation(location);

        rule.apply("/",_request,_response);
        assertRedirectResponse(HttpStatus.FOUND_302,location);
    }
