    @Test
    public void testSetBadInteger() throws Exception
    {
        XmlConfiguration xmlConfiguration = new XmlConfiguration("" +
                "<Configure class=\"org.eclipse.jetty.xml.XmlConfigurationTest$NativeHolder\">" +
                "  <Set name=\"integer\">bad</Set>" +
                "</Configure>");

        try
        {
            xmlConfiguration.configure();
            Assert.fail();
        }
        catch (Exception e)
        {
            
        }
    }
