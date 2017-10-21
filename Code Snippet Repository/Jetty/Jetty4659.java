    @Test
    public void testSimpleExpand()
    {
        Props props = new Props();
        props.setProperty("name","jetty",FROM_TEST);
        props.setProperty("version","9.1",FROM_TEST);

        assertThat(props.expand("port=8080"),is("port=8080"));
        assertThat(props.expand("jdk=${java.version}"),is("jdk=" + System.getProperty("java.version")));
        assertThat(props.expand("id=${name}-${version}"),is("id=jetty-9.1"));
        assertThat(props.expand("id=${unknown}-${wibble}"),is("id=${unknown}-${wibble}"));
    }
