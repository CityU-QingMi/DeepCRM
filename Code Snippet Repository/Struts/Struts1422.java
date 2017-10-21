    public void testCanAccessListSizeProperty() {
        ValueStack vs = ActionContext.getContext().getValueStack();
        List myList = new ArrayList();
        myList.add("a");
        myList.add("b");

        ListHolder listHolder = new ListHolder();
        listHolder.setStrings(myList);

        vs.push(listHolder);

        assertEquals(new Integer(myList.size()), vs.findValue("strings.size()"));
        assertEquals(new Integer(myList.size()), vs.findValue("strings.size"));
    }
