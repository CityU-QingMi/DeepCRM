    public void testUnknownNameDefined() throws Exception {
        ActionTag tag = new ActionTag();
        tag.setPageContext(pageContext);
        tag.setNamespace("");
        tag.setName("UNKNOWN_NAME");
        tag.setExecuteResult(false);

        tag.doStartTag();
        tag.doEndTag();
        // will just log it to ERROR but we run the code to test that it works somehow
    }
