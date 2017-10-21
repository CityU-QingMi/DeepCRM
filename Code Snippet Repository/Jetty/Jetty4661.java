    @Test
    public void testExpandDeep()
    {
        Props props = new Props();
        props.setProperty("name","jetty",FROM_TEST);
        props.setProperty("version","9.1",FROM_TEST);
        props.setProperty("id","${name}-${version}",FROM_TEST);

        // Should expand
        assertThat(props.expand("server-id=corporate-${id}"),is("server-id=corporate-jetty-9.1"));
    }
