    @Test
    public void testFormattingWithNulls()
    {
        jul.clear();

        JavaUtilLog log = new JavaUtilLog("test.nu.ll");
        setJulLevel("test.nu.ll",Level.INFO);

        log.info("Testing info(msg,null,null) - {} {}","arg0","arg1");
        log.info("Testing info(msg,null,null) - {}/{}",null,null);
        log.info("Testing info(msg,null,null) > {}",null,null);
        log.info("Testing info(msg,null,null)",null,null);
        log.info(null,"Testing","info(null,arg0,arg1)");
        log.info(null,null,null);

        //jul.dump();

        jul.assertContainsLine("INFO|test.nu.ll|Testing info(msg,null,null) - null/null");
        jul.assertContainsLine("INFO|test.nu.ll|Testing info(msg,null,null) > null null");
        jul.assertContainsLine("INFO|test.nu.ll|Testing info(msg,null,null) null null");
        jul.assertContainsLine("INFO|test.nu.ll|null Testing info(null,arg0,arg1)");
        jul.assertContainsLine("INFO|test.nu.ll|null null null");
    }
