    public void testChart() throws Exception {
        EasyMock.expect(responseMock.getOutputStream()).andReturn(os);
        EasyMock.replay(responseMock, mockActionProxy, actionInvocation);
        
        ChartResult result = new ChartResult();

        result.setChart(mockChart);

        result.setHeight("10");
        result.setWidth("10");
        result.execute(actionInvocation);

        EasyMock.verify(responseMock);
        assertTrue(os.isWritten());
    }
