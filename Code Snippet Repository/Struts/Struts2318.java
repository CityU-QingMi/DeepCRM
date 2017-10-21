    public void testHeaderCanBePreselected() throws Exception {
        SelectTag tag = new SelectTag();
        tag.setPageContext(pageContext);
        tag.setLabel("myLabel");
        tag.setList("#{1:'Cat',2:'Dog'}");
        tag.setName("myPet");
        tag.setHeaderKey("-1");
        tag.setHeaderValue("--- Please Select ---");
        tag.setValue("%{'-1'}");

        tag.doStartTag();
        tag.doEndTag();

        verify(SelectTag.class.getResource("Select-8.txt"));
    }
