    public void testInputForm() {
        setScriptingEnabled(false);
        beginAt("/tags/ui/example!input.action");
        assertFormPresent("exampleSubmit");
        // text box
        assertFormElementPresent("name");
        // textarea
        assertFormElementPresent("bio");
        // select
        assertFormElementPresent("favouriteColor");
        // checkbox list
        assertFormElementPresent("friends");
        // checkbox
        assertFormElementPresent("legalAge");

        // set fields
        setTextField("name", "name");
        setTextField("bio", "bio");
        selectOption("favouriteColor", "Red");
        checkCheckbox("friends", "Patrick");
        checkCheckbox("friends", "Jason");
        checkCheckbox("legalAge");

        submit();

        assertTextInElement("name", "name");
        assertTextInElement("bio", "bio");
        assertTextInElement("favouriteColor", "Red");
        assertTextInElement("friends", "[Patrick, Jason]");
        assertTextInElement("legalAge", "true");
    }
