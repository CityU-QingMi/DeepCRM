    @Test
    public void testMessageStringBodyIgnored() throws Exception {
        this.setUp(null);

        final MockBodyContent content = new MockBodyContent("This is more body content 02.", (Writer)null);
        this.tag.setBodyContent(content);
        this.tag.setMessage("This is another message 02.");

        assertEquals("The message is not correct.", "This is another message 02.", this.tag.getMessage());
    }
