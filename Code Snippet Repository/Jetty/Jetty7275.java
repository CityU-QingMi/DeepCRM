    @Test
    public void testTwoArgumentListConstructorArg() throws Exception
    {
        XmlConfiguration xmlConfiguration = new XmlConfiguration("<Configure class=\"org.eclipse.jetty.xml.TestConfiguration\">"
                + "<Set name=\"constructorArgTestClass\"><New class=\"org.eclipse.jetty.xml.ConstructorArgTestClass\">"
                + "<Arg type=\"List\">" + STRING_ARRAY_XML + "</Arg>"
                + "<Arg type=\"List\">" + STRING_ARRAY_XML + "</Arg>"
                + "</New></Set></Configure>");
        TestConfiguration tc = new TestConfiguration();
        assertThat("tc.getList() returns null as it's not configured yet",tc.getList(),is(nullValue()));
        xmlConfiguration.configure(tc);
        assertThat("tc.getList() returns not null",tc.getList(),not(nullValue()));
        assertThat("tc.getList() has two entries as specified in the xml", tc.getList().size(), is(2));
    }
