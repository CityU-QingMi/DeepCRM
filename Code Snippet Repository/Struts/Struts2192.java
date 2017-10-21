    public void testYesActionMessages() throws Exception {

        ActionMessageTag tag = new ActionMessageTag();
        tag.setId("someid");
        ((InternalActionSupport)action).setHasActionMessage(true);
        tag.setPageContext(pageContext);
        tag.doStartTag();
        tag.doEndTag();

        verify(ActionMessageTagTest.class.getResource("actionmessage-2.txt"));
    }
