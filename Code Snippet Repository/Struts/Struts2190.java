    public void testActionMessageEscape() throws Exception {

           ActionMessageTag tag = new ActionMessageTag();
           TestAction testAction = new TestAction();
           testAction.addActionMessage("<p>hey</p>");
           stack.pop();
           stack.push(testAction);
           tag.setEscape(true);
           tag.setPageContext(pageContext);
           tag.doStartTag();
           tag.doEndTag();

           assertEquals(normalize("<ul class=\"actionMessage\"><li><span>&lt;p&gt;hey&lt;/p&gt;</span></li></ul>", true),
                   normalize(writer.toString(), true));
       }
