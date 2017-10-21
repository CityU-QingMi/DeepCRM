    @Test
    public void testStatusCodeWithReason() throws IOException
    {
        for (int i = 1; i < 400; i++)
        {
            _rule.setCode("" + i);
            _rule.setReason("reason" + i);
            _rule.apply(null, _request, _response);

            assertEquals(i, _response.getStatus());
            assertEquals(null, _response.getReason());
        }
    }
