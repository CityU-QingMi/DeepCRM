    public void testDefaultMessageOk() throws Exception {
        // NOTE:
        // simulate the condition
        // <s:text name="some.invalid.key">My Default Message</s:text>

        StrutsMockBodyContent mockBodyContent = new StrutsMockBodyContent(new MockJspWriter());
        mockBodyContent.setString("Sample Of Default Message");
        tag.setBodyContent(mockBodyContent);
        tag.setName("some.invalid.key.so.we.should.get.the.default.message");
        int startStatus = tag.doStartTag();
        tag.doEndTag();

        assertEquals(startStatus, BodyTag.EVAL_BODY_BUFFERED);
        assertEquals("Sample Of Default Message", writer.toString());
    }
