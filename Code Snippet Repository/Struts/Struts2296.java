    public void testOptGroupNumbers() throws Exception {
    	
    	((TestAction)action).setMap(new LinkedHashMap() {{
    		put("AAA", "aaa");
    		put(111111L, "bbb");
    		put("CCC", "ccc");
    	}});
    	
        SelectTag selectTag = new SelectTag();
        selectTag.setName("mySelection");
        selectTag.setLabel("My Selection");
        selectTag.setList("%{#{'ONE':'one','TWO':'two','THREE':'three'}}");

        OptGroupTag optGroupTag1 = new OptGroupTag();
        optGroupTag1.setLabel("My Label 1");
        optGroupTag1.setList("map");

        OptGroupTag optGroupTag2 = new OptGroupTag();
        optGroupTag2.setLabel("My Label 2");
        optGroupTag2.setList("%{#{'DDD':'ddd','EEE':'eee','FFF':'fff'}}");

        selectTag.setPageContext(pageContext);
        selectTag.doStartTag();
        optGroupTag1.setPageContext(pageContext);
        optGroupTag1.doStartTag();
        optGroupTag1.doEndTag();
        optGroupTag2.setPageContext(pageContext);
        optGroupTag2.doStartTag();
        optGroupTag2.doEndTag();
        selectTag.doEndTag();


        //System.out.println(writer.toString());
        verify(SelectTag.class.getResource("OptGroup-4.txt"));
    }
