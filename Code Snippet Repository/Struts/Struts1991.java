    public void testMakeSelectListMethod() throws Exception {
        String[] selectedList = new String[] { "Car", "Airplane", "Bus" };
        List list = new ArrayList();
        list.add("Lorry");
        list.add("Car");
        list.add("Helicopter");

        stack.getContext().put("mySelectedList", selectedList);
        stack.getContext().put("myList", list);

        List listMade = strutsUtil.makeSelectList("#mySelectedList", "#myList", null, null);

        assertEquals(listMade.size(), 3);
        assertEquals(((ListEntry)listMade.get(0)).getKey(), "Lorry");
        assertEquals(((ListEntry)listMade.get(0)).getValue(), "Lorry");
        assertEquals(((ListEntry)listMade.get(0)).getIsSelected(), false);
        assertEquals(((ListEntry)listMade.get(1)).getKey(), "Car");
        assertEquals(((ListEntry)listMade.get(1)).getValue(), "Car");
        assertEquals(((ListEntry)listMade.get(1)).getIsSelected(), true);
        assertEquals(((ListEntry)listMade.get(2)).getKey(), "Helicopter");
        assertEquals(((ListEntry)listMade.get(2)).getValue(), "Helicopter");
        assertEquals(((ListEntry)listMade.get(2)).getIsSelected(), false);
    }
