    private void assertMatch(boolean flag, String[] matchCase) throws IOException
    {
        _rule.setPattern(matchCase[0]);
        final String uri=matchCase[1];
        
        String result = _rule.matchAndApply(uri,
        new Request(null,null)
        {
            {
                setMetaData(new MetaData.Request("GET",new HttpURI(uri),HttpVersion.HTTP_1_0,new HttpFields()));
            }
        }, null
        );

        assertEquals("pattern: " + matchCase[0] + " uri: " + matchCase[1], flag, result!=null);
    }
