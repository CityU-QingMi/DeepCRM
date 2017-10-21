    public void testParameterizable() throws Exception {
        Mock mock = new Mock(Parameterizable.class);

        MockActionInvocation mai = new MockActionInvocation();
        MockActionProxy map = new MockActionProxy();
        ActionConfig ac = new ActionConfig.Builder("", "", "").build();

        Map params = ac.getParams();

        map.setConfig(ac);
        mai.setProxy(map);
        mai.setAction(mock.proxy());
        mock.expect("setParams", params);

        interceptor.intercept(mai);
        mock.verify();
    }
