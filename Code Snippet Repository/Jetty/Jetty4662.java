    @Test
    public void testExpandDouble()
    {
        Props props = new Props();
        props.setProperty("bar","apple",FROM_TEST);
        props.setProperty("foo","foo/${bar}/${bar}-xx",FROM_TEST);

        // Should expand
        assertThat(props.expand("foo/${bar}/${bar}-xx"),is("foo/apple/apple-xx"));
    }
