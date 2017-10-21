    public void testNullError() throws Exception {

        ActionErrorTag tag = new ActionErrorTag();
        tag.setId("someid");
        ((InternalActionSupport)action).setHasActionErrors(true);
        ((InternalActionSupport)action).addActionError(null);
        tag.setPageContext(pageContext);
        tag.doStartTag();
        tag.doEndTag();

        verify(ActionErrorTagTest.class.getResource("actionerror-2.txt"));
    }
