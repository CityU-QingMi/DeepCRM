    public void testContentTypeJpg() throws Exception {
        EasyMock.expect(responseMock.getOutputStream()).andReturn(os);
        responseMock.setContentType("image/jpg");
        EasyMock.replay(responseMock, mockActionProxy, actionInvocation);
        ChartResult result = new ChartResult();

        result.setChart(mockChart);

        result.setHeight("10");
        result.setWidth("10");
        result.setType("jpg");
        result.execute(actionInvocation);

        EasyMock.verify(responseMock);
        assertTrue(os.isWritten());
    }
