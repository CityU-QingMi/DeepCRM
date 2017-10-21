    public void testMultipleOn() throws Exception {
        SelectTag tag = new SelectTag();
        tag.setPageContext(pageContext);
        tag.setLabel("media1");
        tag.setId("myId");
        tag.setEmptyOption("true");
        tag.setName("myName");
        tag.setMultiple("true");
        tag.setList("{'aaa','bbb'}");

        tag.doStartTag();
        tag.doEndTag();

        verify(SelectTag.class.getResource("Select-5.txt"));
    }
