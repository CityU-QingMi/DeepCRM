    public void testWithEmptyOptionAndHeader() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("banana");

        List l = new ArrayList();
        l.add("apple");
        l.add("banana");
        l.add("pineaple");
        l.add("grapes");
        testAction.setCollection(l);

        ComboBoxTag tag = new ComboBoxTag();
        tag.setPageContext(pageContext);
        tag.setLabel("My Favourite Fruit");
        tag.setName("myFavouriteFruit");
        tag.setEmptyOption("true");
        tag.setHeaderKey("-1");
        tag.setHeaderValue("--- Please Select ---");
        tag.setList("collection");
        tag.setValue("%{foo}");

        tag.doStartTag();
        tag.doEndTag();

        verify(ComboBoxTag.class.getResource("ComboBox-2.txt"));
    }
