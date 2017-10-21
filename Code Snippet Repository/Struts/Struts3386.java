    @SMDMethod
    public Bean getBean() {
        Bean bean = new Bean();
        bean.setStringField("str");
        bean.setBooleanField(true);
        bean.setCharField('s');
        bean.setDoubleField(10.1);
        bean.setFloatField(1.5f);
        bean.setIntField(10);
        bean.setLongField(100);
        return bean;
    }
