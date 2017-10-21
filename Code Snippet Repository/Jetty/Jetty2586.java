    private void assertMatch(boolean flag, String[] matchCase) throws IOException
    {
        _rule.setRegex(matchCase[0]);
        final String uri=matchCase[1];
        String result = _rule.matchAndApply(uri,
        new Request(null,null)
        {
            @Override
            public String getRequestURI()
            {
                return uri;
            }
        }, null
        );

        assertEquals("regex: " + matchCase[0] + " uri: " + matchCase[1], flag, result!=null);
    }
