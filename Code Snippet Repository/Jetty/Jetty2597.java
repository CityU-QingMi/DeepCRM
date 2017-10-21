    @Test
    public void testRequestAndReplacementWithQueryString() throws IOException
    {
        String requestQueryString = "request=parameter";
        String replacement = "/replace?given=param";
        String[] split = replacement.split("\\?", 2);
        String path = split[0];
        String queryString = split[1];
        _request.setURIPathQuery("/old/context");
        _request.setQueryString(requestQueryString);

        RewritePatternRule rewritePatternRule = new RewritePatternRule();
        rewritePatternRule.setPattern("/old/context");
        rewritePatternRule.setReplacement(replacement);

        String result = rewritePatternRule.matchAndApply("/old/context", _request, _response);
        assertThat(result, is(path));

        rewritePatternRule.applyURI(_request, null, result);
        assertThat("queryString matches expected", _request.getQueryString(),
                is(requestQueryString + "&" + queryString));
        assertThat("request URI matches expected", _request.getRequestURI(), is(path));
    }
