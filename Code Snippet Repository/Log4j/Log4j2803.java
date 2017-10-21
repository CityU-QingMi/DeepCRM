    @Test
    public void appendManyEvents() throws Exception {
        final Logger logger = CTX.getLogger();
        ThreadContext.put("test", "mdc");
        ThreadContext.push("ndc");
        for (int i = 0; i < 20; i++) {
            logger.info(MarkerManager.getMarker("MARKER"), "Test log message");
        }
        ThreadContext.clearAll();

        TimeUnit.SECONDS.sleep(3);

        int i = 0;
        try (final Session session = CASSANDRA.connect()) {
            for (final Row row : session.execute("SELECT * FROM logs")) {
                assertNotNull(row.get("id", UUID.class));
                assertNotNull(row.get("timeid", UUID.class));
                assertNotNull(row.get("timestamp", Date.class));
                assertEquals("Test log message", row.getString("message"));
                assertEquals("MARKER", row.getString("marker"));
                assertEquals("INFO", row.getString("level"));
                assertEquals(getClass().getName(), row.getString("logger"));
                final Map<String, String> mdc = row.getMap("mdc", String.class, String.class);
                assertEquals(1, mdc.size());
                assertEquals("mdc", mdc.get("test"));
                final List<String> ndc = row.getList("ndc", String.class);
                assertEquals(1, ndc.size());
                assertEquals("ndc", ndc.get(0));
                ++i;
            }
        }
        assertEquals(20, i);
    }
