    @Test
    public void testOverride()
    {
        Props props = new Props();
        props.setProperty("name","jetty",FROM_TEST);
        props.setProperty("name","altjetty","(Alt-Jetty)");

        String prefix = "Overriden";
        assertThat(prefix,props.getString("name"),is("altjetty"));

        Prop prop = props.getProp("name");
        assertProp(prefix,prop,"name","altjetty","(Alt-Jetty)");
        Prop older = prop.overrides;
        assertThat(prefix + ".overrides",older,notNullValue());
        assertProp(prefix + ".overridden",older,"name","jetty",FROM_TEST);
        assertThat(prefix + ".overridden",older.overrides,nullValue());
    }
