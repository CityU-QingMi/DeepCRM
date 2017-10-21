    public void testSerializeJobData() throws IOException, NoSuchDelegateException {
        StdJDBCDelegate delegate = new StdJDBCDelegate();
        delegate.initialize(LoggerFactory.getLogger(getClass()), "QRTZ_", "TESTSCHED", "INSTANCE", new SimpleClassLoadHelper(), false, "");
        
        JobDataMap jdm = new JobDataMap();
        delegate.serializeJobData(jdm).close();

        jdm.clear();
        jdm.put("key", "value");
        jdm.put("key2", null);
        delegate.serializeJobData(jdm).close();

        jdm.clear();
        jdm.put("key1", "value");
        jdm.put("key2", null);
        jdm.put("key3", new Object());
        try {
            delegate.serializeJobData(jdm);
            fail();
        } catch (NotSerializableException e) {
            assertTrue(e.getMessage().indexOf("key3") >= 0);
        }
    }
