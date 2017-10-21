    public void testXW377() {
        localizedTextProvider.addDefaultResourceBundle("com/opensymphony/xwork2/util/LocalizedTextUtilTest");

        String text = localizedTextProvider.findText(Bar.class, "xw377", ActionContext.getContext().getLocale(), "xw377", null, ActionContext.getContext().getValueStack());
        assertEquals("xw377", text); // should not log

        String text2 = localizedTextProvider.findText(StrutsLocalizedTextProviderTest.class, "notinbundle", ActionContext.getContext().getLocale(), "hello", null, ActionContext.getContext().getValueStack());
        assertEquals("hello", text2); // should log WARN

        String text3 = localizedTextProvider.findText(StrutsLocalizedTextProviderTest.class, "notinbundle.key", ActionContext.getContext().getLocale(), "notinbundle.key", null, ActionContext.getContext().getValueStack());
        assertEquals("notinbundle.key", text3); // should log WARN

        String text4 = localizedTextProvider.findText(StrutsLocalizedTextProviderTest.class, "xw377", ActionContext.getContext().getLocale(), "hello", null, ActionContext.getContext().getValueStack());
        assertEquals("xw377", text4); // should not log

        String text5 = localizedTextProvider.findText(StrutsLocalizedTextProviderTest.class, "username", ActionContext.getContext().getLocale(), null, null, ActionContext.getContext().getValueStack());
        assertEquals("Santa", text5); // should not log
    }
