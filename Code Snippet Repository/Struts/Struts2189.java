    public void testNoActionMessages() throws Exception {

        ActionMessageTag tag = new ActionMessageTag();
        ((InternalActionSupport)action).setHasActionMessage(false);
        tag.setPageContext(pageContext);
        tag.doStartTag();
        tag.doEndTag();

        verify(ActionMessageTagTest.class.getResource("actionmessage-1.txt"));
    }
