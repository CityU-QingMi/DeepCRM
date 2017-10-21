    @Test
    public void testMatchAndApplyAndApplyURI() throws IOException
    {
        for (String[] test : _tests)
        {
            _rule.setPattern(test[1]);
            String result = _rule.matchAndApply(test[0], _request, _response);
            assertThat(test[1], test[2], is(result));

            _rule.applyURI(_request, null, result);
            assertThat(_request.getRequestURI(), is(test[2]));
        }
    }
