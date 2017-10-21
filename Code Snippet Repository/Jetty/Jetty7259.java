    @Test
    public void testNestedConstructorNamedInjectionUnorderedMixed() throws Exception
    {
        XmlConfiguration xmlConfiguration = new XmlConfiguration("" +
                "<Configure class=\"org.eclipse.jetty.xml.AnnotatedTestConfiguration\">" +
                "  <Arg name=\"third\">arg3</Arg>  " +
                "  <Arg>arg2</Arg>  " +
                "  <Arg name=\"first\">arg1</Arg>  " +
                "  <Set name=\"nested\">  " +
                "    <New class=\"org.eclipse.jetty.xml.AnnotatedTestConfiguration\">" +
                "      <Arg name=\"third\">arg3</Arg>  " +
                "      <Arg>arg2</Arg>  " +
                "      <Arg name=\"first\">arg1</Arg>  " +
                "    </New>" +
                "  </Set>" +
                "</Configure>");

        AnnotatedTestConfiguration atc = (AnnotatedTestConfiguration)xmlConfiguration.configure();

        Assert.assertEquals("first parameter not wired correctly","arg1", atc.getFirst());
        Assert.assertEquals("second parameter not wired correctly","arg2", atc.getSecond());
        Assert.assertEquals("third parameter not wired correctly","arg3", atc.getThird());
        Assert.assertEquals("nested first parameter not wired correctly","arg1", atc.getNested().getFirst());
        Assert.assertEquals("nested second parameter not wired correctly","arg2", atc.getNested().getSecond());
        Assert.assertEquals("nested third parameter not wired correctly", "arg3", atc.getNested().getThird());
    }
