       public void testActionErrorsDontEscape() throws Exception {

           ActionMessageTag tag = new ActionMessageTag();
           TestAction testAction = new TestAction();
           testAction.addActionMessage("<p>hey</p>");
           stack.pop();
           stack.push(testAction);
           tag.setEscape(false);
           tag.setPageContext(pageContext);
           tag.doStartTag();
           tag.doEndTag();

           assertEquals(normalize("<ul class=\"actionMessage\"><li><span><p>hey</p></span></li></ul>", true),
                   normalize(writer.toString(), true));
       }
