    @SuppressWarnings("")
    public <T> TestBean<T> newInstance(Class<T> clazz) throws Exception
    {
        TestBean<T> testBean = new TestBean<>();
        Set<Bean<?>> beans = container.getBeanManager().getBeans(clazz,AnyLiteral.INSTANCE);
        if (beans.size() > 0)
        {
            testBean.bean = (Bean<T>)beans.iterator().next();
            testBean.cCtx = container.getBeanManager().createCreationalContext(testBean.bean);
            testBean.instance = (T)container.getBeanManager().getReference(testBean.bean,clazz,testBean.cCtx);
            return testBean;
        }
        else
        {
            throw new Exception(String.format("Can't find class %s",clazz));
        }
    }
