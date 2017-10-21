    public CloseableLocalWebSocketSession(WebSocketContainerScope containerScope, TestName testname, EventDriver driver)
    {
        super(containerScope, testname, driver);
        // LifeCycle start
        try
        {
            start();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
