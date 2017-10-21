    @Setup
    public void setUp() throws Exception {
        System.setProperty("log4j.configurationFile", "log4j2-appenderComparison.xml");
        deleteLogFiles();

        fileLogger = LogManager.getLogger("FileLogger");
        rafLogger = LogManager.getLogger("RAFLogger");
        mmapLogger = LogManager.getLogger("MMapLogger");
        consoleLogger = LogManager.getLogger("ConsoleLogger");
        directConsoleLogger = LogManager.getLogger("DirectConsoleLogger");
        noopLogger = LogManager.getLogger("NoopLogger");
        rewriteLogger = LogManager.getLogger("RewriteLogger");

        fileAppender = ((org.apache.logging.log4j.core.Logger) fileLogger).getAppenders().get("File");
        rafAppender = ((org.apache.logging.log4j.core.Logger) rafLogger).getAppenders().get("RandomAccessFile");
        mmapAppender = ((org.apache.logging.log4j.core.Logger) mmapLogger).getAppenders().get("MemoryMappedFile");
        consoleAppender = ((org.apache.logging.log4j.core.Logger) consoleLogger).getAppenders().get("Console");
        directConsoleAppender = ((org.apache.logging.log4j.core.Logger) directConsoleLogger).getAppenders().get("DirectConsole");
        noopAppender = ((org.apache.logging.log4j.core.Logger) noopLogger).getAppenders().get("NoOp");
        rewriteAppender = ((org.apache.logging.log4j.core.Logger) rewriteLogger).getAppenders().get("Rewrite");
    }
