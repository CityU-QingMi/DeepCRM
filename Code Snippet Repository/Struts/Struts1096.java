    public void testSimpleValue() throws Exception {
        // given
        String expected = System.getenv("USER");
        ValueSubstitutor substitutor = new EnvsValueSubstitutor();

        // when
        String actual = substitutor.substitute("${env.USER}");

        // then
        assertEquals(expected, actual);
    }
