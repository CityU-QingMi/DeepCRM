    public void testSimpleWithStringMap() throws Exception {
        final Map<String, String> myMap = new TreeMap<String, String>();
        myMap.put("name", "Std.");
        stack.push(new HashMap() {{ put ("myMap", myMap); }});

        RadioTag tag = new RadioTag();
        tag.setPageContext(pageContext);
        tag.setName("myMap['name']");
        tag.setList("#@java.util.TreeMap@{\"Opt.\":\"Opt.\", \"Std.\":\"Std.\", \"\":\"N/A\"}");
        tag.doStartTag();
        tag.doEndTag();

        verify(RadioTag.class.getResource("Radio-6.txt"));
    }
