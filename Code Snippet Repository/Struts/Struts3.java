    public void testPostOrderInHtml() {
        beginAt("/orders/new.xhtml");
        setWorkingForm(0);
        setTextField("clientName", "Test2");
        setTextField("amount", "321");
        try {
            submit();
        } catch (FailingHttpStatusCodeException ex) {
            // ignore;
        }
        assertTextPresent("Test2");
        assertLinkNotPresentWithText("Back to Orders");
    }
