    public void testSimple() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("hello");

        ArrayList collection = new ArrayList();
        collection.add("foo");
        collection.add("bar");
        collection.add("baz");
        testAction.setCollection(collection);

        ComboBoxTag tag = new ComboBoxTag();
        tag.setPageContext(pageContext);
        tag.setLabel("mylabel");
        tag.setName("foo");
        tag.setId("cb");
        tag.setList("collection");

        tag.doStartTag();
        tag.doEndTag();

        verify(ComboBoxTag.class.getResource("ComboBox-1.txt"));
    }
