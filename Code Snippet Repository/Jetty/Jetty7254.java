    @Test
    public void testStringConfiguration() throws Exception
    {
        XmlConfiguration configuration =
            new XmlConfiguration("<Configure class=\"org.eclipse.jetty.xml.TestConfiguration\"><Set name=\"Test\">SetValue</Set><Set name=\"Test\" type=\"int\">2</Set></Configure>");
        TestConfiguration tc = new TestConfiguration();
        configuration.configure(tc);
        assertEquals("Set String 3", "SetValue", tc.testObject);
        assertEquals("Set Type 3", 2, tc.testInt);
    }
