    public void testActionErrorComponentDisposeItselfFromComponentStack() throws Exception {
        ActionMessageTag t = new ActionMessageTag();
        t.setPageContext(pageContext);

        try {
            t.doStartTag();
            ActionErrorTag tag = new ActionErrorTag();
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
