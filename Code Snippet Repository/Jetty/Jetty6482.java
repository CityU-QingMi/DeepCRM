    @Test
    public void testParameterMap() throws Exception
    {
        JettyTrackingSocket wsocket = new JettyTrackingSocket();

        URI wsUri = server.getWsUri().resolve("/test?snack=cashews&amount=handful&brand=off");
        Future<Session> future = client.connect(wsocket,wsUri);

        IBlockheadServerConnection ssocket = server.accept();
        ssocket.upgrade();

        future.get(30,TimeUnit.SECONDS);

        Assert.assertTrue(wsocket.openLatch.await(1,TimeUnit.SECONDS));

        Session session = wsocket.getSession();
        UpgradeRequest req = session.getUpgradeRequest();
        Assert.assertThat("Upgrade Request",req,notNullValue());

        Map<String, List<String>> parameterMap = req.getParameterMap();
        Assert.assertThat("Parameter Map",parameterMap,notNullValue());

        Assert.assertThat("Parameter[snack]",parameterMap.get("snack"),is(Arrays.asList(new String[] { "cashews" })));
        Assert.assertThat("Parameter[amount]",parameterMap.get("amount"),is(Arrays.asList(new String[] { "handful" })));
        Assert.assertThat("Parameter[brand]",parameterMap.get("brand"),is(Arrays.asList(new String[] { "off" })));

        Assert.assertThat("Parameter[cost]",parameterMap.get("cost"),nullValue());
    }
