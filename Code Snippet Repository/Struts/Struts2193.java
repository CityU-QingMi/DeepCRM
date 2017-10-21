     public void testYesActionMessagesWithEmptyMessages() throws Exception {

        ActionMessageTag tag = new ActionMessageTag();
        tag.setId("someid");
        InternalActionSupport internalActionSupport = (InternalActionSupport) action;
        internalActionSupport.setJustNullElement(true);
        tag.setPageContext(pageContext);
        tag.doStartTag();
        tag.doEndTag();

       assertTrue(StringUtils.isBlank(writer.toString()));
    }
