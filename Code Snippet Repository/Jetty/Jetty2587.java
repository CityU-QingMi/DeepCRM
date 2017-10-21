    @Test
    public void testStatusCodeNoReason() throws IOException
    {
        for (int i = 1; i < 400; i++)
        {
            _rule.setCode("" + i);
            _rule.apply(null, _request, _response);

            assertEquals(i, _response.getStatus());
        }
    }
