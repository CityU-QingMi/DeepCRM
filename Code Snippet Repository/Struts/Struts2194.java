    public void testNullMessage() throws Exception {

        ActionMessageTag tag = new ActionMessageTag();
        tag.setId("someid");
        ((InternalActionSupport)action).setHasActionMessage(true);
        ((InternalActionSupport)action).addActionMessage(null);
        tag.setPageContext(pageContext);
        tag.doStartTag();
        tag.doEndTag();

        verify(ActionMessageTagTest.class.getResource("actionmessage-2.txt"));
    }
