    @Test
    public void testBasic()
    {
        Props props = new Props();
        props.setProperty("name","jetty",FROM_TEST);

        String prefix = "Basic";
        assertThat(prefix,props.getString("name"),is("jetty"));

        Prop prop = props.getProp("name");
        assertProp(prefix,prop,"name","jetty",FROM_TEST);
        assertThat(prefix + ".overrides",prop.overrides,nullValue());
    }
