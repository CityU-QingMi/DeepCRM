    public void testNoActionErrors() throws Exception {
        ActionErrorTag tag = new ActionErrorTag();
        ((InternalActionSupport)action).setHasActionErrors(false);
        tag.setPageContext(pageContext);
        tag.doStartTag();
        tag.doEndTag();

        //assertEquals("", writer.toString());
        verify(ActionErrorTagTest.class.getResource("actionerror-1.txt"));
    }
