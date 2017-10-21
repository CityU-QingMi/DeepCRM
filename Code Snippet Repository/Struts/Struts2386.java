    public void testDisableMoveUpButton() throws Exception {
        UpDownSelectTag tag = new UpDownSelectTag();
        tag.setPageContext(pageContext);
        tag.setId("myId");
        tag.setName("myName");
        tag.setList("myMap");
        tag.setValue("mySelectedMapIds");
        tag.setEmptyOption("true");
        tag.setAllowMoveUp("false");

        tag.doStartTag();
        tag.doEndTag();

        verify(UpDownSelectTagTest.class.getResource("updownselecttag-7.txt"));
    }
