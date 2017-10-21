    public void testOptGroupWithSingleSelect() throws Exception {

        SelectTag selectTag = new SelectTag();
        selectTag.setName("mySelection");
        selectTag.setLabel("My Selection");
        selectTag.setList("%{#{'ONE':'one','TWO':'two','THREE':'three'}}");
        selectTag.setValue("%{'EEE'}");

        OptGroupTag optGroupTag1 = new OptGroupTag();
        optGroupTag1.setLabel("My Label 1");
        optGroupTag1.setList("%{#{'AAA':'aaa','BBB':'bbb','CCC':'ccc'}}");

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
        verify(SelectTag.class.getResource("OptGroup-2.txt"));
    }
