    @Test
    public void testNewObject() throws Exception
    {
        final String newDefaultValue = "NEW DEFAULT";
        TestConfiguration.VALUE=71;

        URL url = SpringXmlConfigurationTest.class.getClassLoader().getResource(_configure);
        final AtomicInteger count = new AtomicInteger(0);
        XmlConfiguration configuration = new XmlConfiguration(url)
        {
            @Override
            public void initializeDefaults(Object object)
            {
                super.initializeDefaults(object);
                if (object instanceof TestConfiguration)
                {
                    count.incrementAndGet();
                    ((TestConfiguration)object).setTestString0(newDefaultValue);
                    ((TestConfiguration)object).setTestString1("WILL BE OVERRIDDEN");
                }
            }
        };

        Map<String,String> properties = new HashMap<String,String>();
        properties.put("test", "xxx");

        TestConfiguration nested = new TestConfiguration();
        nested.setTestString0("nested");
        configuration.getIdMap().put("nested", nested);

        configuration.getProperties().putAll(properties);
        TestConfiguration tc = (TestConfiguration)configuration.configure();

        assertEquals(3,count.get());

        assertEquals(newDefaultValue, tc.getTestString0());
        assertEquals(-1, tc.getTestInt0());
        assertEquals("SetValue", tc.getTestString1());
        assertEquals(1, tc.getTestInt1());

        assertEquals(newDefaultValue, tc.getNested().getTestString0());
        assertEquals("nested", tc.getNested().getTestString1());
        assertEquals(newDefaultValue, tc.getNested().getNested().getTestString0());
        assertEquals("deep", tc.getNested().getNested().getTestString1());

        assertEquals("deep", ((TestConfiguration)configuration.getIdMap().get("nestedDeep")).getTestString1());
        assertEquals(2, ((TestConfiguration)configuration.getIdMap().get("nestedDeep")).getTestInt2());

        assertEquals("xxx", tc.getTestString2());
    }
