    @Test
    public void testParameterizedMessage_NullValues() throws Exception
    {
        StdErrLog log = new StdErrLog(StdErrLogTest.class.getName(),new Properties());
        log.setLevel(StdErrLog.LEVEL_DEBUG);
        try (StacklessLogging stackless = new StacklessLogging(log))
        {
            log.info("Testing info(msg,null,null) - {} {}","arg0","arg1");
            log.info("Testing info(msg,null,null) - {} {}",null,null);
            log.info("Testing info(msg,null,null) - {}",null,null);
            log.info("Testing info(msg,null,null)",null,null);
            log.info(null,"Testing","info(null,arg0,arg1)");
            log.info(null,null,null);

            log.debug("Testing debug(msg,null,null) - {} {}","arg0","arg1");
            log.debug("Testing debug(msg,null,null) - {} {}",null,null);
            log.debug("Testing debug(msg,null,null) - {}",null,null);
            log.debug("Testing debug(msg,null,null)",null,null);
            log.debug(null,"Testing","debug(null,arg0,arg1)");
            log.debug(null,null,null);

            log.debug("Testing debug(msg,null)");
            log.debug(null,new Throwable("Testing debug(null,thrw)").fillInStackTrace());

            log.warn("Testing warn(msg,null,null) - {} {}","arg0","arg1");
            log.warn("Testing warn(msg,null,null) - {} {}",null,null);
            log.warn("Testing warn(msg,null,null) - {}",null,null);
            log.warn("Testing warn(msg,null,null)",null,null);
            log.warn(null,"Testing","warn(msg,arg0,arg1)");
            log.warn(null,null,null);

            log.warn("Testing warn(msg,null)");
            log.warn(null,new Throwable("Testing warn(msg,thrw)").fillInStackTrace());
        }
    }
