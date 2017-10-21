    public void testChartNotSet() {
        ChartResult result = new ChartResult();
        EasyMock.replay(responseMock, mockActionProxy, actionInvocation);
        
        // expect exception if chart not set.
        result.setChart(null);

        try {
            result.execute(actionInvocation);
            fail();
        } catch (Exception e) {
        }

        EasyMock.verify(responseMock);
        assertFalse(os.isWritten());
    }
