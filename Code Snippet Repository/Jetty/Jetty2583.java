    @Test
    public void testMovedPermanently() throws IOException
    {
        RedirectRegexRule rule = new RedirectRegexRule();
        rule.setRegex("/api/(.*)$");
        rule.setReplacement("http://api.company.com/$1");
        rule.setStatusCode(HttpStatus.MOVED_PERMANENTLY_301);

        // Resource is api with parameters
        rule.matchAndApply("/api/rest/foo?id=100&sort=date", _request, _response);
        assertRedirectResponse(HttpStatus.MOVED_PERMANENTLY_301,"http://api.company.com/rest/foo?id=100&sort=date");
    }
