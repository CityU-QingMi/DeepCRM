    @Test
    public void testErrorStatusWithReason() throws IOException
    {
        for (int i = 400; i < 600; i++)
        {
            _rule.setCode("" + i);
            _rule.setReason("reason-" + i);
            _rule.apply(null, _request, _response);

            assertEquals(i, _response.getStatus());
            assertEquals("reason-" + i, _response.getReason());
            super.reset();
        }
    }
