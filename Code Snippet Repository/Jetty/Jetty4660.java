    @Test
    public void testNoExpandDoubleDollar()
    {
        Props props = new Props();
        props.setProperty("aa","123",FROM_TEST);

        // Should NOT expand double $$ symbols
        assertThat(props.expand("zz=$${aa}"),is("zz=${aa}"));
        // Should expand
        assertThat(props.expand("zz=${aa}"),is("zz=123"));
    }
