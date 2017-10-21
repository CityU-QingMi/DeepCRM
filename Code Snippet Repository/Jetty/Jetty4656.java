    @Test
    public void testSystemPropsOnly()
    {
        Props props = new Props();

        String expected = System.getProperty("java.io.tmpdir");
        assertThat("System Property",props.getString("java.io.tmpdir"),is(expected));

        Prop prop = props.getProp("java.io.tmpdir");
        assertProp("System Prop",prop,"java.io.tmpdir",expected,Props.ORIGIN_SYSPROP);
        assertThat("System Prop.overrides",prop.overrides,nullValue());
    }
