    public void testGetTextsWithListArgs() throws Exception {
        List<Object> args = new ArrayList<>();
        args.add("Santa");
        args.add("loud");
        assertEquals("Hello World", mas.getText("hello", "this is default", args)); // no args in bundle
        assertEquals("Hello World Santa", mas.getText("hello.0", "this is default", args)); // only 1 arg in bundle
        assertEquals("Hello World. This is Santa speaking loud", mas.getText("hello.1", "this is default", args));

        assertEquals("this is default", mas.getText("not.in.bundle", "this is default", args));
        assertEquals("this is default Santa", mas.getText("not.in.bundle", "this is default {0}", args));
        assertEquals("this is default Santa speaking loud", mas.getText("not.in.bundle", "this is default {0} speaking {1}", args));

        assertEquals("Hello World", mas.getText("hello", args)); // no args in bundle
        assertEquals("Hello World Santa", mas.getText("hello.0", args)); // only 1 arg in bundle
        assertEquals("Hello World. This is Santa speaking loud", mas.getText("hello.1", args));

        assertEquals("not.in.bundle", mas.getText("not.in.bundle", args));

        assertEquals("Hello World", mas.getText("hello", "this is default", (List) null));
        assertEquals("this is default", mas.getText("not.in.bundle", "this is default", (List) null));
    }
