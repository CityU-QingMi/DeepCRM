    public void testToObjectList() throws IOException {

        List<Contact> source = new ArrayList<Contact>();
        source.add(new Contact("bob", true, 44));
        source.add(new Contact("john", false, 33));

        List<Contact> target = new ArrayList<Contact>();
        StringReader reader = new StringReader("[{\"age\":44,\"important\":true,\"name\":\"bob\"},{\"age\":33,\"important\":false,\"name\":\"john\"}]");
        JacksonLibHandler handler = new JacksonLibHandler();
        handler.toObject(new MockActionInvocation(), reader, target);
        assertEquals(source.size(), target.size());
    }
