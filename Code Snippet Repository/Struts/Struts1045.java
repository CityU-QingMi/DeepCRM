    public void testGetTextsWithArrayAndStack() throws Exception {
        String[] args = { "Santa", "loud" };
        assertEquals("Hello World", tp.getText("hello", "this is default", args, null)); // no args in bundle
        assertEquals("Hello World Santa", tp.getText("hello.0", "this is default", args, null)); // only 1 arg in bundle
        assertEquals("Hello World. This is Santa speaking loud", tp.getText("hello.1", "this is default", args, null));

        assertEquals("this is default", tp.getText("not.in.bundle", "this is default", args, null));
        assertEquals("this is default Santa", tp.getText("not.in.bundle", "this is default {0}", args, null));
        assertEquals("this is default Santa speaking loud", tp.getText("not.in.bundle", "this is default {0} speaking {1}", args, null));
    }
