    public void testEnumList() throws Exception {

        SelectTag tag = new SelectTag();
        tag.setPageContext(pageContext);
        tag.setLabel("mylabel");
        tag.setName("status");
        tag.setList("statusList");
        tag.setListKey("name");
        tag.setListValue("displayName");

        tag.doStartTag();
        tag.doEndTag();

        verify(SelectTag.class.getResource("Select-13.txt"));
    }
