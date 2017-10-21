    public void testWithNoMessageAndBodyIsNotEmptyBodyIsReturned() throws Exception {
        final String key = "key.does.not.exist";
        final String bodyText = "body text";
        tag.setName(key);

        StrutsBodyContent bodyContent = new StrutsBodyContent(null);
        bodyContent.print(bodyText);
        tag.setBodyContent(bodyContent);
        tag.doStartTag();
        tag.doEndTag();
        assertEquals(bodyText, writer.toString());
    }
