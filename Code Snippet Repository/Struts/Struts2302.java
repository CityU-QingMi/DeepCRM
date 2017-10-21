    public void testMapCheckedNull() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("bar");

        HashMap map = new HashMap();
        map.put("1", "One");
        map.put("2", "Two");
        testAction.setMap(map);

        RadioTag tag = new RadioTag();
        tag.setPageContext(pageContext);
        tag.setLabel("mylabel");
        tag.setName("myname");
        tag.setValue("%{map['3']}");
        tag.setList("#@java.util.TreeMap@{\"1\":\"One\", \"2\":\"Two\", \"\":\"N/A\"}");

        tag.doStartTag();
        tag.doEndTag();

        verify(RadioTag.class.getResource("Radio-4.txt"));
    }
