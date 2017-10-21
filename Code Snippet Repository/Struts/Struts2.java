    public void testPostOrderWithErrors() {
        beginAt("/orders/new");
        setWorkingForm(0);
        setTextField("amount", "321");
        try {
            submit();
        } catch (FailingHttpStatusCodeException ex) {
            // ignore;
        }
        assertTextPresent("client name is empty");
        assertTextFieldEquals("amount", "321");
    }
