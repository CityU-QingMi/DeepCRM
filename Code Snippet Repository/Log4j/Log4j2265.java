    @Test
    public void testEventMapMessage() {
      final HashMap<String, String> map = new HashMap<>();
      map.put("A", "B");
      final HashMap<String, String> eventMap = new HashMap<>();
      eventMap.put("A1", "B1");
      final StringMapMessage message = new StringMapMessage(eventMap);
      final LogEvent event = Log4jLogEvent.newBuilder()
                .setMessage(message)
                .build();
      final MapLookup lookup = new MapLookup(map);
      assertEquals("B", lookup.lookup(event, "A"));
      assertEquals("B1", lookup.lookup(event, "A1"));
    }
