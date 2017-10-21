    public void testGetText() {
        try {
            ActionProxy proxy = actionProxyFactory.createActionProxy("", MockConfigurationProvider.FOO_ACTION_NAME, null, null);
            ActionContext.getContext().setLocale(Locale.US);

            TextProvider localeAware = (TextProvider) proxy.getAction();
            assertEquals("Foo Range Message", localeAware.getText("foo.range"));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
