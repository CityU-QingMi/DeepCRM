    @SuppressWarnings({""})
    @Test
    public void testPropertiesWithoutSubstitution() {
        assertNull("null propertiesList", createForProperties(null).getPropertyList());
        assertNull("null property Map", createForProperties(null).getProperties());

        final Property[] all = new Property[] {
                Property.createProperty("key1", "value1"),
                Property.createProperty("key2", "value2"),
        };
        final LoggerConfig loggerConfig = createForProperties(all);
        final List<Property> list = loggerConfig.getPropertyList();
        assertEquals("map and list contents equal", new HashSet<>(list),
        		     new HashSet<>(loggerConfig.getPropertyList()));

        final Object[] actualList = new Object[1];
        loggerConfig.setLogEventFactory(new LogEventFactory() {
            @Override
            public LogEvent createEvent(final String loggerName, final Marker marker, final String fqcn,
                    final Level level, final Message data,
                    final List<Property> properties, final Throwable t) {
                actualList[0] = properties;
                return new Builder().setTimeMillis(System.currentTimeMillis()).build();
            }
        });
        loggerConfig.log("name", "fqcn", null, Level.INFO, new SimpleMessage("msg"), null);
        assertSame("propertiesList passed in as is if no substitutions required", list, actualList[0]);
    }
