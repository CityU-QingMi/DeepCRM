    public void testCRUD() throws Exception {
        JPAOAuthManagerImpl omgr = (JPAOAuthManagerImpl)
            WebloggerFactory.getWeblogger().getOAuthManager();

        String consumerKey = "1111";
        OAuthConsumer consumer = omgr.addConsumer("dummyusername", consumerKey);
        TestUtils.endSession(true);

        consumer = omgr.getConsumerByKey(consumer.consumerKey);
        assertNotNull(consumer);
        assertEquals(consumerKey, consumer.consumerKey);

        OAuthAccessor accessor = new OAuthAccessor(consumer);
        accessor.setProperty("userId", "dummyusername");
        omgr.addAccessor(accessor);
        TestUtils.endSession(true);

        accessor = omgr.getAccessorByKey(consumerKey);
        assertNotNull(accessor);

        omgr.removeAccessor(accessor);
        TestUtils.endSession(true);
        assertNull(omgr.getAccessorByKey(consumerKey));

        omgr.removeConsumer(consumer);
        TestUtils.endSession(true);
        assertNull(omgr.getConsumerByKey(consumerKey));
    }
