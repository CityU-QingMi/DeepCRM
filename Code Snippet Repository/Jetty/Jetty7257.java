    @Test
    public void testArgumentsGetIgnoredMissingDTD() throws Exception
    {
        XmlConfiguration xmlConfiguration = new XmlConfiguration(new ByteArrayInputStream(("" +
                "<Configure class=\"org.eclipse.jetty.xml.AnnotatedTestConfiguration\">" +
                "  <Arg>arg1</Arg>  " +
                "  <Arg>arg2</Arg>  " +
                "  <Arg>arg3</Arg>  " +
                "  <Set name=\"nested\">  " +
                "    <New class=\"org.eclipse.jetty.xml.AnnotatedTestConfiguration\">\n" + 
                "      <Arg>arg1</Arg>\n" + 
                "      <Arg>arg2</Arg>\n" + 
                "      <Arg>arg3</Arg>\n" + 
                "    </New>" +
                "  </Set>" +
                "</Configure>").getBytes(StandardCharsets.ISO_8859_1)));
//        XmlConfiguration xmlConfiguration = new XmlConfiguration(url);

        AnnotatedTestConfiguration atc = (AnnotatedTestConfiguration)xmlConfiguration.configure();

        Assert.assertEquals("first parameter not wired correctly","arg1", atc.getFirst());
        Assert.assertEquals("second parameter not wired correctly","arg2", atc.getSecond());
        Assert.assertEquals("third parameter not wired correctly","arg3", atc.getThird());
        Assert.assertEquals("nested first parameter not wired correctly","arg1", atc.getNested().getFirst());
        Assert.assertEquals("nested second parameter not wired correctly","arg2", atc.getNested().getSecond());
        Assert.assertEquals("nested third parameter not wired correctly", "arg3", atc.getNested().getThird());
    }
