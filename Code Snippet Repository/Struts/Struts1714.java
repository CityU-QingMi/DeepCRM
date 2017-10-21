    public void testOptiontransferselectComponentDisposeItselfFromComponentStack() throws Exception {
        ActionErrorTag t = new ActionErrorTag();
        t.setPageContext(pageContext);

        try {
            t.doStartTag();
            OptionTransferSelectTag tag = new OptionTransferSelectTag();
            tag.setId("myId");
            tag.setDoubleId("myDoubleId");
            tag.setName("name");
            tag.setLabel("label");
            tag.setList("{}");
            tag.setDoubleList("{}");
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
