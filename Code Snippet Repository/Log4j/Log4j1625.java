    @Test
    public void testSimpleMap() {
        final Logger logger = context.getLogger(CollectionLoggingTest.class.getName());
        logger.error(System.getProperties());
        final Map<String, String> map = new HashMap<>();
        map.put("MyKey1", "MyValue1");
        map.put("MyKey2", "MyValue2");
        logger.error(new StringMapMessage(map));
        logger.error(map);
        // TODO: some assertions
    }
