    public void testSimple() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("bar");

        TextareaTag tag = new TextareaTag();
        tag.setPageContext(pageContext);
        tag.setLabel("mylabel");
        tag.setName("myname");
        tag.setValue("%{foo}");
        tag.setRows("30");
        tag.setCols("20");
        tag.setTitle("mytitle");
        tag.setDisabled("true");
        tag.setTabindex("5");
        tag.setOnchange("alert('goodbye');");
        tag.setOnclick("alert('onclick');");
        tag.setId("the_id");
        tag.setOnkeyup("alert('hello');");
        tag.setReadonly("true");

        tag.doStartTag();
        tag.doEndTag();

        verify(TextareaTag.class.getResource("Textarea-1.txt"));
    }
