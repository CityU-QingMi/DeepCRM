    public void testDoubleselectComponentDisposeItselfFromComponentStack() throws Exception {
        ActionErrorTag t = new ActionErrorTag();
        t.setPageContext(pageContext);

        try {
            t.doStartTag();
            DoubleSelectTag tag = new DoubleSelectTag();
            tag.setName("name");
            tag.setLabel("label");
            tag.setList("#{1:'one',2:'two'}");
            tag.setDoubleName("doubleName");
            tag.setDoubleList("1?({'aa','bb'}:{'cc','dd'}");
            tag.setFormName("formName");
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
