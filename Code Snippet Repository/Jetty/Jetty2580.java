    @Test
    public void testLocationWithPathReplacement() throws IOException
    {
        RedirectRegexRule rule = new RedirectRegexRule();
        rule.setRegex("/documentation/(.*)$");
        rule.setReplacement("/docs/$1");

        // Resource is dir
        rule.matchAndApply("/documentation/top.html", _request, _response);
        assertRedirectResponse(HttpStatus.FOUND_302,"http://0.0.0.0/docs/top.html");
    }
