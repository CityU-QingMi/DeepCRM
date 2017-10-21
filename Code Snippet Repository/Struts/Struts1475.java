    public void testXW404() {
        // This tests will try to load bundles from the 3 locales but we only have files for France and Germany.
        // Before this fix loading the bundle for Germany failed since Italy have previously failed and thus the misses cache
        // contained a false entry

        ResourceBundle rbFrance = localizedTextProvider.findResourceBundle("com/opensymphony/xwork2/util/XW404", Locale.FRANCE);
        ResourceBundle rbItaly = localizedTextProvider.findResourceBundle("com/opensymphony/xwork2/util/XW404", Locale.ITALY);
        ResourceBundle rbGermany = localizedTextProvider.findResourceBundle("com/opensymphony/xwork2/util/XW404", Locale.GERMANY);

        assertNotNull(rbFrance);
        assertEquals("Bonjour", rbFrance.getString("hello"));

        assertNull(rbItaly);

        assertNotNull(rbGermany);
        assertEquals("Hallo", rbGermany.getString("hello"));
    }
