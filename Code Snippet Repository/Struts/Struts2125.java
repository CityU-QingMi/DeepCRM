    public void testSimpleRoundingCeiling() throws Exception {
        // given
        context.put(ActionContext.LOCALE, Locale.US);

        TestAction testAction = (TestAction) action;
        testAction.setFloatNumber(120.45f);

        NumberTag tag = new NumberTag();
        tag.setPageContext(pageContext);
        tag.setName("floatNumber");
        tag.setRoundingMode("down");

        // when
        tag.doStartTag();
        tag.doEndTag();

        // then
        NumberFormat format = NumberFormat.getInstance((Locale) context.get(ActionContext.LOCALE));
        format.setRoundingMode(RoundingMode.DOWN);
        String expected = format.format(120.45f);

        assertEquals(expected, writer.toString());
    }
