    @Test
    public void testLocationWithReplacementGroupEmpty() throws IOException
    {
        RedirectRegexRule rule = new RedirectRegexRule();
        rule.setRegex("/my/dir/file/(.*)$");
        rule.setReplacement("http://www.mortbay.org/$1");

        // Resource is dir
        rule.matchAndApply("/my/dir/file/", _request, _response);
        assertRedirectResponse(HttpStatus.FOUND_302,"http://www.mortbay.org/");
    }
