    public void testMapChecked() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("bar");

        Map<String, String> map = new LinkedHashMap<>();
        map.put("1", "One");
        map.put("2", "Two");
        testAction.setMap(map);

        RadioTag tag = new RadioTag();
        tag.setPageContext(pageContext);
        tag.setLabel("mylabel");
        tag.setName("myname");
        tag.setValue("\"1\"");
        tag.setList("map");
        tag.setListKey("key");
        tag.setListValue("value");

        tag.doStartTag();
        tag.doEndTag();

        verify(RadioTag.class.getResource("Radio-2.txt"));
    }
