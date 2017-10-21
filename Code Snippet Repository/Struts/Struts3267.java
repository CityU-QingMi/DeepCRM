    protected void setUp() throws Exception {
        super.setUp();

        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("Java", new Double(43.2));
        data.setValue("Visual Basic", new Double(0.0));
        data.setValue("C/C++", new Double(17.5));
        mockChart = ChartFactory.createPieChart("Pie Chart", data, true, true, false);


        stack = ActionContext.getContext().getValueStack();
        ActionContext.getContext().setValueStack(stack);


        mockActionProxy = EasyMock.createNiceMock(ActionProxy.class);
        EasyMock.expect(mockActionProxy.getNamespace()).andReturn("/html");

        actionInvocation = EasyMock.createMock(ActionInvocation.class);

        EasyMock.expect(actionInvocation.getStack()).andReturn(stack).anyTimes();
        
        
        os = new MockServletOutputStream();
        responseMock = EasyMock.createNiceMock(HttpServletResponse.class);

        ServletActionContext.setResponse((HttpServletResponse) responseMock);
    }
