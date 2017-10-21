    private void prepareTagGeneric(CheckboxListTag tag) {
        TestAction testAction = (TestAction) action;
        Collection<String> collection = new ArrayList<String>(2);
        collection.add("hello");
        collection.add("foo");
        testAction.setCollection(collection);
        testAction.setList(new String[][]{
                {"hello", "world"},
                {"foo", "bar"},
        });
        tag.setName("collection");
        tag.setList("list");
        tag.setListKey("top[0]");
        tag.setListValue("top[1]");
    }
