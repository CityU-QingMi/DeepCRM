    public void testArrayOfDoubles() throws Exception {
        val.setMinInclusive(10d);
        val.setMaxInclusive(14d);

        val.setFieldName("doubleArray");
        val.setDefaultMessage("Value ${currentValue} not in scope!");

        MyTestProduct object = new MyTestProduct();
        object.setDoubleArray(new Double[]{11d, 15d});

        DummyValidatorContext context = new DummyValidatorContext(object, tpf);
        val.setValidatorContext(context);

        val.validate(object);

        assertTrue(context.hasFieldErrors());
        assertEquals(1, context.getFieldErrors().size());
        assertEquals(1, context.getFieldErrors().get("doubleArray").size());
        assertEquals("Value 15.0 not in scope!", context.getFieldErrors().get("doubleArray").get(0));
    }
