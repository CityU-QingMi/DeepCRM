    @Test
    public void testRequestWithQueryString() throws IOException
    {
        String replacement = "/replace";
        String queryString = "request=parameter";
        _request.setURIPathQuery("/old/context");
        _request.setQueryString(queryString);

        RewritePatternRule rewritePatternRule = new RewritePatternRule();
        rewritePatternRule.setPattern("/old/context");
        rewritePatternRule.setReplacement(replacement);

        String result = rewritePatternRule.matchAndApply("/old/context", _request, _response);
        assertThat("result matches expected", result, is(replacement));

        rewritePatternRule.applyURI(_request, null, result);
        assertThat("queryString matches expected", _request.getQueryString(), is(queryString));
        assertThat("request URI matches expected", _request.getRequestURI(), is(replacement));
    }
