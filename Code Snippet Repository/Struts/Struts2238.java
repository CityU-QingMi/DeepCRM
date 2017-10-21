    private void prepareTagGeneric(DoubleSelectTag tag) {
        TestAction testAction = (TestAction) action;
        Region antwerp = new Region("Antwerp", "AN");
        Region gent = new Region("Gent", "GN");
        Region brugge = new Region("Brugge", "BRG");
        ArrayList belgiumRegions = new ArrayList();
        belgiumRegions.add(antwerp);
        belgiumRegions.add(gent);
        belgiumRegions.add(brugge);
        Country belgium = new Country("Belgium", "BE", belgiumRegions);

        Region paris = new Region("Paris", "PA");
        Region bordeaux = new Region("Bordeaux", "BOR");
        ArrayList franceRegions = new ArrayList();
        franceRegions.add(paris);
        franceRegions.add(bordeaux);
        Country france = new Country("France", "FR", franceRegions);

        Collection collection = new ArrayList(2);
        collection.add("AN");
        testAction.setCollection(collection);

        tag.setList("collection");

        List countries = new ArrayList();
        countries.add(belgium);
        countries.add(france);

        testAction.setList2(countries);

        tag.setValue("'FR'");
        tag.setDoubleValue("'BOR'");

        tag.setList("list2");
        tag.setDoubleList("regions");
        tag.setDoubleName("region");

        tag.setListKey("iso");
        tag.setDoubleListKey("key");
        tag.setListValue("name");
        tag.setDoubleListValue("name");

        tag.setFormName("inputForm");
    }
