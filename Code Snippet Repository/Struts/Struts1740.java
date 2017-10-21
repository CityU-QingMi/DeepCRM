    public void testDefaultResurceBundlePropertyLoaded() throws Exception {
        LocalizedTextProvider localizedTextProvider = container.getInstance(LocalizedTextProvider.class);

        // some i18n messages from xwork-messages.properties
        assertEquals(localizedTextProvider.findDefaultText("xwork.error.action.execution", Locale.US),
                "Error during Action invocation");

        // some i18n messages from struts-messages.properties
        assertEquals(localizedTextProvider.findDefaultText("struts.messages.error.uploading", Locale.US,
                        new Object[] { "some error messages" }),
                "Error uploading: some error messages");
    }
