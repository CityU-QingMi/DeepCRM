    public void testWithMap() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("banana");

        Map m = new LinkedHashMap();
        m.put("apple", "apple");
        m.put("banana", "banana");
        m.put("pineaple", "pineaple");
        m.put("grapes", "grapes");
        testAction.setMap(m);

        ComboBoxTag tag = new ComboBoxTag();
        tag.setPageContext(pageContext);
        tag.setLabel("My Favourite Fruit");
        tag.setName("myFavouriteFruit");
        tag.setHeaderKey("-1");
        tag.setHeaderValue("--- Please Select ---");
        tag.setEmptyOption("true");
        tag.setList("map");
        tag.setValue("%{foo}");

        tag.doStartTag();
        tag.doEndTag();

        verify(ComboBoxTag.class.getResource("ComboBox-3.txt"));
    }
