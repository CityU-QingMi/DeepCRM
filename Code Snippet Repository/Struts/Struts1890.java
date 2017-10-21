    @Override
    public void setUp() throws Exception {
        super.setUp();

        HttpServletResponse response = EasyMock.createNiceControl().createMock(HttpServletResponse.class);
        response.isCommitted();
        EasyMock.expectLastCall().andReturn(Boolean.FALSE);
        EasyMock.replay(response);

        ServletActionContext.setResponse(response);
    }
