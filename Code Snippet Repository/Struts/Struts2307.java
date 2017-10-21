    private void prepareTagGeneric(RadioTag tag) {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("bar");
        testAction.setList(new String[][]{
                {"hello", "world"},
                {"foo", "bar"}
        });
        tag.setList("list");
        tag.setListKey("top[0]");
        tag.setListValue("top[1]");
    }
