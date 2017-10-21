    private void assertHeaders(String headers[][]) throws IOException
    {
        for (String[] header : headers)
        {
            _rule.setName(header[0]);
            _rule.setValue(header[1]);

            _rule.apply(null, _request, _response);

            assertEquals(header[1], _response.getHeader(header[0]));
        }
    }
