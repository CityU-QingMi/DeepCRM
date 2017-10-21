    @Test
    public void testPassedObject() throws Exception
    {
        TestConfiguration.VALUE=77;

        URL url = SpringXmlConfigurationTest.class.getClassLoader().getResource(_configure);
        XmlConfiguration configuration = new XmlConfiguration(url);

        Map<String,String> properties = new HashMap<>();
        properties.put("test", "xxx");

        TestConfiguration nested = new TestConfiguration();
        nested.setTestString0("nested");
        configuration.getIdMap().put("nested",nested);

        TestConfiguration tc = new TestConfiguration();
        tc.setTestString0("preconfig");
        tc.setTestInt0(42);
        configuration.getProperties().putAll(properties);

        tc=(TestConfiguration)configuration.configure(tc);

        assertEquals("preconfig", tc.getTestString0());
        assertEquals(42, tc.getTestInt0());
        assertEquals("SetValue", tc.getTestString1());
        assertEquals(1, tc.getTestInt1());

        assertEquals("nested", tc.getNested().getTestString0());
        assertEquals("nested", tc.getNested().getTestString1());
        assertEquals("default", tc.getNested().getNested().getTestString0());
        assertEquals("deep", tc.getNested().getNested().getTestString1());

        assertEquals("deep", ((TestConfiguration)configuration.getIdMap().get("nestedDeep")).getTestString1());
        assertEquals(2, ((TestConfiguration)configuration.getIdMap().get("nestedDeep")).getTestInt2());

        assertEquals("xxx", tc.getTestString2());
    }
