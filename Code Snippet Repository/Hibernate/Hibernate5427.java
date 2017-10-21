    @Test
	@TestForIssue( jiraKey = "")
    public void testParseColonCharacterEscaped() {
        final StringBuilder captured = new StringBuilder();
        Recognizer recognizer = new Recognizer() {
            @Override
            public void outParameter(int position) {
                fail();
            }
            @Override
            public void ordinalParameter(int position) {
                fail();
            }
            @Override
            public void namedParameter(String name, int position) {
                fail();
            }
            @Override
            public void jpaPositionalParameter(String name, int position) {
                fail();
            }
            @Override
            public void other(char character) {
                captured.append(character);
            }
        };
        ParameterParser.parse("SELECT @a,(@a::=20) FROM tbl_name", recognizer);
        assertEquals("SELECT @a,(@a:=20) FROM tbl_name", captured.toString());
    }
