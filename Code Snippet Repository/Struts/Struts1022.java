    private void checkGetTexts(MyActionSupport mas) {
        assertEquals("Hello World", mas.getText("hello"));
        assertEquals("not.in.bundle", mas.getText("not.in.bundle"));

        assertEquals("Hello World", mas.getText("hello", "this is default"));
        assertEquals("this is default", mas.getText("not.in.bundle", "this is default"));

        List nullList = null;
        assertEquals("Hello World", mas.getText("hello", nullList));

        String[] nullStrings = null;
        assertEquals("Hello World", mas.getText("hello", nullStrings));
    }
