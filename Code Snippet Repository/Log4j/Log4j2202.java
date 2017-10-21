    @Test
    public void gelfLayout() throws IOException {
        final Logger logger = context.getLogger();
        logger.info("Message");
        final String gelf = context.getListAppender("list").getMessages().get(0);
        final ObjectMapper mapper = new ObjectMapper();
        final JsonNode json = mapper.readTree(gelf);
        assertEquals("Message", json.get("short_message").asText());
        assertEquals("myhost", json.get("host").asText());
        assertEquals("FOO", json.get("_foo").asText());
        assertEquals(new JavaLookup().getRuntime(), json.get("_runtime").asText());
    }
