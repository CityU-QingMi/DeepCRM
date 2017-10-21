    public void testTokenComponentDisposeItselfFromComponentStack() throws Exception {
        ActionErrorTag t = new ActionErrorTag();
        t.setPageContext(pageContext);

        try {
            t.doStartTag();
            TokenTag tag = new TokenTag();
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
