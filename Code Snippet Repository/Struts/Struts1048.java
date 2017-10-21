    public void testLocaleGetText() {
        try {
            ActionProxy proxy = actionProxyFactory.createActionProxy("", MockConfigurationProvider.FOO_ACTION_NAME, null, null);
            ActionContext.getContext().setLocale(Locale.GERMANY);

            TextProvider localeAware = (TextProvider) proxy.getAction();
            assertEquals("I don't know German", localeAware.getText("foo.range"));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
