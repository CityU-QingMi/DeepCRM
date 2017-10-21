    @BeforeClass
    public static void setJUL()
    {
        LogManager lmgr = LogManager.getLogManager();
        java.util.logging.Logger root = lmgr.getLogger("");
        // Remember original handlers
        originalHandlers = root.getHandlers();
        // Remove original handlers
        for (Handler existing : originalHandlers)
        {
            root.removeHandler(existing);
        }
        // Set test/capturing handler
        jul = new CapturingJULHandler();
        root.addHandler(jul);
    }
