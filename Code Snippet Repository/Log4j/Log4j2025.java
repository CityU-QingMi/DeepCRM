    @Test
    public void testPropertiesWithSubstitution() {
        final Property[] all = new Property[] {
                Property.createProperty("key1", "value1-${sys:user.name}"),
                Property.createProperty("key2", "value2-${sys:user.name}"),
        };
        final LoggerConfig loggerConfig = createForProperties(all);
        final List<Property> list = loggerConfig.getPropertyList();
        assertEquals("map and list contents equal", new HashSet<>(list), 
        		     new HashSet<>(loggerConfig.getPropertyList()));

        final Object[] actualListHolder = new Object[1];
        loggerConfig.setLogEventFactory(new LogEventFactory() {
            @Override
            public LogEvent createEvent(final String loggerName, final Marker marker, final String fqcn,
                    final Level level, final Message data,
                    final List<Property> properties, final Throwable t) {
                actualListHolder[0] = properties;
                return new Builder().setTimeMillis(System.currentTimeMillis()).build();
            }
        });
        loggerConfig.log("name", "fqcn", null, Level.INFO, new SimpleMessage("msg"), null);
        assertNotSame("propertiesList with substitutions", list, actualListHolder[0]);

        @SuppressWarnings("unchecked")
		final List<Property> actualList = (List<Property>) actualListHolder[0];

        for (int i = 0; i < list.size(); i++) {
            assertEquals("name[" + i + "]", list.get(i).getName(), actualList.get(i).getName());
            final String value = list.get(i).getValue().replace("${sys:user.name}", System.getProperty("user.name"));
            assertEquals("value[" + i + "]", value, actualList.get(i).getValue());
        }
    }
