    public void testLocale() {
        Locale defLocale = Locale.getDefault();
        ActionContext.getContext().setLocale(null);

        // will never return null, if no locale is set then default is returned
        assertNotNull(as.getLocale());
        assertEquals(defLocale, as.getLocale());

        ActionContext.getContext().setLocale(Locale.ITALY);
        assertEquals(Locale.ITALY, as.getLocale());

        ActionContext.setContext(new ActionContext(new HashMap<String, Object>()));
        assertEquals(defLocale, as.getLocale()); // ActionContext will create a new context, when it was set to null before
    }
