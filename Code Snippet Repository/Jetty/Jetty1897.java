    @Test
    public void testOpenPojo()
    {
        validator = ValidatorBuilder.create().with(new SetterTester()).with(new GetterTester()).build();
        List<Class> classes = Arrays.asList(MBeanContainer.class,ObjectMBean.class,LogMBean.class);
        for (Class clazz : classes)
        {
            validator.validate(PojoClassFactory.getPojoClass(clazz));
        }
    }
