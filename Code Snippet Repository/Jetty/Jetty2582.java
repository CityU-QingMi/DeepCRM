    @Test
    public void testLocationWithReplacementGroupDeepWithParams() throws IOException
    {
        RedirectRegexRule rule = new RedirectRegexRule();
        rule.setRegex("/my/dir/file/(.*)$");
        rule.setReplacement("http://www.mortbay.org/$1");

        // Resource is api with parameters
        rule.matchAndApply("/my/dir/file/api/rest/foo?id=100&sort=date", _request, _response);
        assertRedirectResponse(HttpStatus.FOUND_302,"http://www.mortbay.org/api/rest/foo?id=100&sort=date");
    }
