    @Override
    protected void log(final int runNumber) {
        if (runNumber == 2) {
            // System.out.println("Set a breakpoint here.");
        }
        final Logger logger = LogManager.getLogger("auditcsvfile");
        final int val1 = 9, val2 = 11, val3 = 12;
        logger.info("Info Message!", val1, val2, val3);
        logger.info("Info Message!", val1, val2, val3);
        logger.info("Info Message!", val1, val2, val3);
    }
