    public void testNoSubstitution() throws Exception {
        // given
        ValueSubstitutor substitutor = new EnvsValueSubstitutor();

        // when
        String actual = substitutor.substitute("val1");

        // then
        assertEquals("val1", actual);
    }
