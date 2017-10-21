    public void testToObject() throws IOException {
        Contact contact = new Contact("bob", true, 44);

        Contact target = new Contact();
        StringReader reader = new StringReader("{\"age\":44,\"important\":true,\"name\":\"bob\"}");
        JsonLibHandler handler = new JsonLibHandler();
        handler.toObject(new MockActionInvocation(), reader, target);

        assertEquals(contact, target);
    }
