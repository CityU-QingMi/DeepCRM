    public String parseWebSocketKey(List<String> requestLines)
    {
        List<String> hits = regexFind(requestLines,"^Sec-WebSocket-Key: (.*)$");
        if (hits.size() <= 0)
        {
            return null;
        }
        
        Assert.assertThat("Number of Sec-WebSocket-Key headers", hits.size(), is(1));
        
        String key = hits.get(0);
        return key;
    }
