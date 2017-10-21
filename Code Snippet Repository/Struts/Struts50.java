    public void testList() {
        beginAt("/conversion/enterPersonsInfo.action");
        setTextField("persons[0].name", "name0");
        setTextField("persons[0].age", "0");
        setTextField("persons[1].name", "name1");
        setTextField("persons[1].age", "1");
        setTextField("persons[2].name", "name2");
        setTextField("persons[2].age", "2");

        submit();

        assertTextPresent("SET 0 Name: name0");
        assertTextPresent("SET 0 Age: 0");
        assertTextPresent("SET 1 Name: name1");
        assertTextPresent("SET 1 Age: 1");
        assertTextPresent("SET 2 Name: name2");
        assertTextPresent("SET 2 Age: 2");
    }
