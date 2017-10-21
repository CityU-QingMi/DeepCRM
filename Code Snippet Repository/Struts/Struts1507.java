    public void testDefaultMessageInterpolation() {
        // get validators
        List validatorList = annotationActionValidatorManager.getValidators(AnnotatedTestBean.class, "beanMessageBundle");
        assertEquals(3, validatorList.size());

        try {
            AnnotatedTestBean bean = new AnnotatedTestBean();
            bean.setName("foo");
            bean.setCount(99);

            ValidatorContext context = new DummyValidatorContext(bean, tpf);
            annotationActionValidatorManager.validate(bean, "beanMessageBundle", context);
            assertTrue(context.hasErrors());
            assertTrue(context.hasFieldErrors());

            List<String> l = context.getFieldErrors().get("count");
            assertNotNull(l);
            assertEquals(1, l.size());
            assertEquals("Smaller Invalid Count: 99", l.get(0));
        } catch (ValidationException ex) {
            ex.printStackTrace();
            fail("Validation error: " + ex.getMessage());
        }
    }
