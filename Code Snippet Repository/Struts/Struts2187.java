     public void testEmptyErrorList() throws Exception {

        ActionErrorTag tag = new ActionErrorTag();
        tag.setId("someid");
        ((InternalActionSupport)action).setHasActionErrors(true);
        ((InternalActionSupport)action).setJustNullElement(true);
        tag.setPageContext(pageContext);
        tag.doStartTag();
        tag.doEndTag();

        assertTrue(StringUtils.isBlank(writer.toString()));
    }
