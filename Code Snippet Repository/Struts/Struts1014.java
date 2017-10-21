    public void testGetTextsWithArrayAndStack() throws Exception {
        ActionContext.getContext().setLocale(new Locale("da"));
        MyActionSupport mas = container.inject(MyActionSupport.class);

        ValueStack stack = ActionContext.getContext().getValueStack();

        String[] args = {"Santa", "loud"};
        assertEquals("Hello World", mas.getText("hello", "this is default", args, stack)); // no args in bundle
        assertEquals("Hello World Santa", mas.getText("hello.0", "this is default", args, stack)); // only 1 arg in bundle
        assertEquals("Hello World. This is Santa speaking loud", mas.getText("hello.1", "this is default", args, stack));

        assertEquals("this is default", mas.getText("not.in.bundle", "this is default", args, stack));
        assertEquals("this is default Santa", mas.getText("not.in.bundle", "this is default {0}", args, stack));
        assertEquals("this is default Santa speaking loud", mas.getText("not.in.bundle", "this is default {0} speaking {1}", args, stack));
    }
