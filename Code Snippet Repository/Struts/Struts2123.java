    public void testSimpleCurrencyUSFormat() throws Exception {
        // given
        context.put(ActionContext.LOCALE, Locale.US);
        
        TestAction testAction = (TestAction) action;
        testAction.setFloatNumber(120.0f);

        NumberTag tag = new NumberTag();
        tag.setPageContext(pageContext);
        tag.setName("floatNumber");
        tag.setType("currency");

        // when
        tag.doStartTag();
        tag.doEndTag();

        // then
        assertEquals("$120.00", writer.toString());
    }
