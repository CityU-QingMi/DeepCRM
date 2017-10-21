    @Test
    public void testLocationWithReplacmentGroupSimple() throws IOException
    {
        RedirectRegexRule rule = new RedirectRegexRule();
        rule.setRegex("/my/dir/file/(.*)$");
        rule.setReplacement("http://www.mortbay.org/$1");

        // Resource is an image
        rule.matchAndApply("/my/dir/file/image.png", _request, _response);
        assertRedirectResponse(HttpStatus.FOUND_302,"http://www.mortbay.org/image.png");
    }
