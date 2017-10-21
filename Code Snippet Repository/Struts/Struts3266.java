    public void testChartWithOGNLProperties() throws Exception {
        EasyMock.expect(responseMock.getOutputStream()).andReturn(os);
        EasyMock.replay(responseMock, mockActionProxy, actionInvocation);


        ChartResult result = new ChartResult();

        result.setChart(mockChart);

        result.setHeight("${myHeight}");
        result.setWidth("${myWidth}");

        ValueStack stack = ActionContext.getContext().getValueStack();
        stack.set("myHeight", 250);
        stack.set("myWidth", 150);

        result.execute(actionInvocation);

        EasyMock.verify(responseMock);
        assertEquals(result.getHeight(), stack.findValue("myHeight").toString());
        assertEquals(result.getWidth(), stack.findValue("myWidth").toString());
        assertEquals("250", result.getHeight().toString());
        assertEquals("150", result.getWidth().toString());
        assertTrue(os.isWritten());
    }
