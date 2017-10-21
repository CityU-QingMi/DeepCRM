    public void testSetCollectionByConverterFromArray() {
        Foo foo = new Foo();
        ValueStack vs = ActionContext.getContext().getValueStack();
        vs.getContext().put(XWorkConverter.REPORT_CONVERSION_ERRORS, Boolean.TRUE);

        XWorkConverter c = (XWorkConverter)((OgnlTypeConverterWrapper) Ognl.getTypeConverter(vs.getContext())).getTarget();
        c.registerConverter(Cat.class.getName(), new FooBarConverter());
        vs.push(foo);

        vs.setValue("cats", new String[]{"1", "2"});
        assertNotNull(foo.getCats());
        assertEquals(2, foo.getCats().size());
        assertEquals(Cat.class, foo.getCats().get(0).getClass());
        assertEquals(Cat.class, foo.getCats().get(1).getClass());
    }
