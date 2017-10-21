    private void executeTest2Action(JSONResult result) throws Exception {
        TestAction action = new TestAction();
        stack.push(action);

        // beans
        Bean bean1 = new Bean();

        bean1.setStringField("str");
        bean1.setBooleanField(true);
        bean1.setCharField('s');
        bean1.setDoubleField(10.1);
        bean1.setFloatField(1.5f);
        bean1.setIntField(10);
        bean1.setLongField(100);
        bean1.setEnumField(AnEnum.ValueA);
        bean1.setEnumBean(AnEnumBean.One);

        // set root
        action.setBean(bean1);
        result.setRoot("bean");

        stack.push(action);
        this.invocation.setStack(stack);
        this.invocation.setAction(action);

        result.execute(this.invocation);
    }
