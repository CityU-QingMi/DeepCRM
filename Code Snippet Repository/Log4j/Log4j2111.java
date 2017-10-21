    @Test
    public void testDotAllPattern() throws Exception {
        final String singleLine = "test single line matches";
        final String multiLine = "test multi line matches\nsome more lines";
        final RegexFilter filter = RegexFilter.createFilter(".*line.*", new String[] { "DOTALL", "COMMENTS" }, false,
                Filter.Result.DENY, Filter.Result.ACCEPT);
        final Result singleLineResult = filter.filter(null, null, null, (Object) singleLine, (Throwable) null);
        final Result multiLineResult = filter.filter(null, null, null, (Object) multiLine, (Throwable) null);
        assertThat(singleLineResult, equalTo(Result.DENY));
        assertThat(multiLineResult, equalTo(Result.DENY));
    }
