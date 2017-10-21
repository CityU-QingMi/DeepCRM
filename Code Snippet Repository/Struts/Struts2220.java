    public void testWithParam() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("bar");

        ComponentTag tag = new ComponentTag();
        tag.setPageContext(pageContext);
        tag.setLabel("mylabel");
        tag.setName("myname");
        tag.setValue("foo");
        tag.setTheme("test");
        tag.setTemplate("Component");

        tag.doStartTag();
        tag.getComponent().addParameter("hello", "world");
        tag.getComponent().addParameter("argle", "bargle");
        tag.getComponent().addParameter("glip", "glop");
        tag.getComponent().addParameter("array", new String[]{"a", "b", "c"});
        tag.getComponent().addParameter("objClass", tag.getClass().getName());
        tag.doEndTag();

        //        System.out.println(writer);
        verify(ComponentTag.class.getResource("Component-param.txt"));
    }
