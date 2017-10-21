    public void testFromObjectArray() throws IOException {
        Contact contact = new Contact("bob", true, 44);

        StringWriter writer = new StringWriter();
        JsonLibHandler handler = new JsonLibHandler();
        handler.fromObject(new MockActionInvocation(), Arrays.asList(contact), "success", writer);

        String data = writer.toString();
        assertTrue(data.startsWith("[{"));
        assertTrue(data.contains("\"age\":44"));
        assertTrue(data.contains("\"important\":true"));
        assertTrue(data.contains("\"name\":\"bob\""));
    }
