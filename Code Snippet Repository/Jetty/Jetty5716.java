    @AfterClass
    public static void restoreJUL()
    {
        LogManager lmgr = LogManager.getLogManager();
        java.util.logging.Logger root = lmgr.getLogger("");
        // Remove test handlers
        for (Handler existing : root.getHandlers())
        {
            root.removeHandler(existing);
        }
        // Restore original handlers
        for (Handler original : originalHandlers)
        {
            root.addHandler(original);
        }
    }
