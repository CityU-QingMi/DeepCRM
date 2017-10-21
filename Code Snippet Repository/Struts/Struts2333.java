    public void testEmptyList() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setList2(new ArrayList());

        SelectTag tag = new SelectTag();
        tag.setName("collection");
        tag.setList("list2");
        tag.setLabel("tmjee_name");

        tag.setPageContext(pageContext);
        tag.doStartTag();
        tag.doEndTag();

        verify(SelectTag.class.getResource("Select-4.txt"));
    }
