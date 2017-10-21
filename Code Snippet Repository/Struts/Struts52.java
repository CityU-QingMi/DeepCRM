    public void testEnum() {
        beginAt("/conversion/enterOperationEnumInfo.action");
        checkCheckbox("selectedOperations", "ADD");
        checkCheckbox("selectedOperations", "MINUS");

        submit();

        assertTextPresent("ADD");
        assertTextPresent("MINUS");        
    }
