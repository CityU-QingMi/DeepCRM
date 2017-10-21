    public void testHaveActionErrors() throws Exception {

        ActionErrorTag tag = new ActionErrorTag();
        tag.setId("someid");
        ((InternalActionSupport)action).setHasActionErrors(true);
        tag.setPageContext(pageContext);
        tag.doStartTag();
        tag.doEndTag();

        verify(ActionErrorTagTest.class.getResource("actionerror-2.txt"));
    }
