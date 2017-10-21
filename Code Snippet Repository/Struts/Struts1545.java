    public void testCollectionOfDoubles() throws Exception {
        val.setMinInclusive(10d);
        val.setMaxInclusive(14d);

        val.setFieldName("doubleCollection");
        val.setDefaultMessage("Value ${currentValue} not in scope!");

        MyTestProduct object = new MyTestProduct();
        object.setDoubleCollection(Arrays.asList(11d, 15d));

        DummyValidatorContext context = new DummyValidatorContext(object, tpf);
        val.setValidatorContext(context);

        val.validate(object);

        assertTrue(context.hasFieldErrors());
        assertEquals(1, context.getFieldErrors().size());
        assertEquals(1, context.getFieldErrors().get("doubleCollection").size());
        assertEquals("Value 15.0 not in scope!", context.getFieldErrors().get("doubleCollection").get(0));
    }
