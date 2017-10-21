    public void testNoNameDefined() throws Exception {
        ActionTag tag = new ActionTag();
        tag.setPageContext(pageContext);
        tag.setNamespace("");
        tag.setName(null);
        tag.setExecuteResult(false);

        try {
            tag.doStartTag();
            tag.doEndTag();
            fail("Should have thrown RuntimeException");
        } catch (StrutsException e) {
             assertEquals("tag 'actioncomponent', field 'name': Action name is required. Example: updatePerson", e.getMessage());
        }
    }
