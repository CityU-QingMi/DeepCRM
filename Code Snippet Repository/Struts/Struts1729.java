    public void testFormComponentDisposeItselfFromComponentStack() throws Exception {
        configurationManager.clearContainerProviders();
        configurationManager.addContainerProvider(new TestConfigurationProvider());
        ActionContext.getContext().setValueStack(stack);

        request.setupGetServletPath("/testAction");

        ActionErrorTag t = new ActionErrorTag();
        t.setPageContext(pageContext);

        try {
            t.doStartTag();
            FormTag tag = new FormTag();
            tag.setName("myForm");
            tag.setMethod("POST");
            tag.setAction("myAction");
            tag.setEnctype("myEncType");
            tag.setTitle("mytitle");
            tag.setPageContext(pageContext);
            tag.doStartTag();
            assertEquals(tag.getComponent().getComponentStack().peek(), tag.getComponent());
            tag.doEndTag();
            assertEquals(t.getComponent().getComponentStack().peek(), t.getComponent());

            t.doEndTag();
        }
        catch (Exception e) {
            e.printStackTrace();
            fail(e.toString());
        }
    }
