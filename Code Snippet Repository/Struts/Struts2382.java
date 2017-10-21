    public void testWithHeaderAndEmptyOption() throws Exception {

        UpDownSelectTag tag = new UpDownSelectTag();
        tag.setPageContext(pageContext);
        tag.setId("myId");
        tag.setName("myName");
        tag.setList("myMap");
        tag.setValue("mySelectedMapIds");
        tag.setEmptyOption("true");
        tag.setHeaderKey("-1");
        tag.setHeaderValue("--- Please Order ---");

        tag.doStartTag();
        tag.doEndTag();

        verify(UpDownSelectTagTest.class.getResource("updownselecttag-3.txt"));
    }
