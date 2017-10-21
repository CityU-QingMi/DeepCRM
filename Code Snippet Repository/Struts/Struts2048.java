    public void testIterableParameters() throws Exception {
        tag.setValue("/TestAction.action?p0=z");

        tag.doStartTag();
        //Iterable
        List<URLTagTest.ValueHolder> list = new ArrayList<URLTagTest.ValueHolder>();
        list.add(new URLTagTest.ValueHolder("a"));
        list.add(new URLTagTest.ValueHolder("b"));
        stack.getContext().put("param1value", list);

        ParamTag param1 = new ParamTag();
        param1.setPageContext(pageContext);
        param1.setName("p1");
        param1.setValue("%{#param1value}");
        param1.doStartTag();
        param1.doEndTag();


        //String[]
        stack.getContext().put("param2value", new String[]{"d", "e"});
        ParamTag param2 = new ParamTag();
        param2.setPageContext(pageContext);
        param2.setName("p2");
        param2.setValue("%{#param2value}");
        param2.doStartTag();
        param2.doEndTag();


        //ValueHolder[]
        stack.getContext().put("param3value", new URLTagTest.ValueHolder[]{
                new URLTagTest.ValueHolder("f"), new URLTagTest.ValueHolder("g")});
        ParamTag param3 = new ParamTag();
        param3.setPageContext(pageContext);
        param3.setName("p3");
        param3.setValue("%{#param3value}");
        param3.doStartTag();
        param3.doEndTag();

        tag.doEndTag();
        
        String result =  writer.toString();
        assertTrue(result.contains("p1=a"));
        assertTrue(result.contains("p1=b"));
        assertTrue(result.contains("p2=d"));
        assertTrue(result.contains("p2=e"));
        assertTrue(result.contains("p3=f"));
        assertTrue(result.contains("p3=g"));
        assertTrue(result.contains("p0=z"));
    }
