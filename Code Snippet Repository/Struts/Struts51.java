    public void testSet() {
        beginAt("/conversion/enterAddressesInfo.action");
        setTextField("addresses('id0').address", "address0");
        setTextField("addresses('id1').address", "address1");
        setTextField("addresses('id2').address", "address2");

        submit();

        assertTextPresent("id0 -> address0");
        assertTextPresent("id1 -> address1");
        assertTextPresent("id2 -> address2");
    }
