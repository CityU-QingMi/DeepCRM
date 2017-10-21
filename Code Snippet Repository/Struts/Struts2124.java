    public void testSimpleCurrencyPLFormat() throws Exception {
        // given
        context.put(ActionContext.LOCALE, new Locale("pl", "PL"));
        
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
        NumberFormat format = NumberFormat.getCurrencyInstance((Locale) context.get(ActionContext.LOCALE));
        format.setRoundingMode(RoundingMode.CEILING);
        String expected = format.format(120.0f);

        assertEquals(expected, writer.toString());
    }
