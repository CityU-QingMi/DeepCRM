    public void testOptGroupWithMultipleSelectIntKey() throws Exception {
      SelectTag selectTag = new SelectTag();
      selectTag.setMultiple("true");
      selectTag.setName("mySelection");
      selectTag.setLabel("My Selection");
      selectTag.setList("%{#{1:'one',2:'two',3:'three'}}");
      selectTag.setValue("%{{22,12,2}}");

      OptGroupTag optGroupTag1 = new OptGroupTag();
      optGroupTag1.setLabel("My Label 1");
      optGroupTag1.setList("%{#{11:'aaa',12:'bbb',13:'ccc'}}");

      OptGroupTag optGroupTag2 = new OptGroupTag();
      optGroupTag2.setLabel("My Label 2");
      optGroupTag2.setList("%{#{21:'ddd',22:'eee',23:'fff'}}");

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
      verify(SelectTag.class.getResource("OptGroup-7.txt"));
  }
