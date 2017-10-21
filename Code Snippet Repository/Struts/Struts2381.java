    public void testWithPartialSelected() throws Exception {

        UpDownSelectTag tag = new UpDownSelectTag();
        tag.setPageContext(pageContext);
        tag.setId("myId");
        tag.setName("myName");
        tag.setList("myMap");
        tag.setValue("mySelectedMapIds");
        tag.setEmptyOption("false");

        tag.doStartTag();
        tag.doEndTag();

        verify(UpDownSelectTagTest.class.getResource("updownselecttag-2.txt"));
    }
