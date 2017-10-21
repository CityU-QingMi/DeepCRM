    public void testIteratorComponentDisposeItselfFromComponentStack() throws Exception {
        TextFieldTag t = new TextFieldTag();
        t.setPageContext(pageContext);
        t.setName("textFieldName");

        IteratorTag tag = new IteratorTag();
        tag.setValue("{1,2}");
        tag.setPageContext(pageContext);

        try {
            t.doStartTag();
            tag.doStartTag();
            assertEquals(tag.getComponent().getComponentStack().peek(), tag.getComponent());
            int endIt = tag.doAfterBody();
            while(TagSupport.EVAL_BODY_AGAIN == endIt) {
                assertEquals(tag.getComponent().getComponentStack().peek(), tag.getComponent());
                endIt = tag.doAfterBody();
            }
            tag.doEndTag();
            assertEquals(t.getComponent().getComponentStack().peek(), t.getComponent());
            t.doEndTag();
        }
        catch(Exception e) {
            e.printStackTrace();
            fail(e.toString());
        }
    }
