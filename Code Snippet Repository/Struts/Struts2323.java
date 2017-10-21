    public void testMultipleOff() throws Exception {
        SelectTag tag = new SelectTag();
        tag.setPageContext(pageContext);
        tag.setLabel("media2");
        tag.setId("myId");
        tag.setEmptyOption("true");
        tag.setName("myName");
        tag.setMultiple("false");
        tag.setList("{'aaa','bbb'}");

        tag.doStartTag();
        tag.doEndTag();

        verify(SelectTag.class.getResource("Select-6.txt"));
    }
