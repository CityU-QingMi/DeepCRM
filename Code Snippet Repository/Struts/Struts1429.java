    public void testChainingProxiedActions() throws Exception {
        ActionProxy proxy = actionProxyFactory.createActionProxy(null, "chainedAOPedTestBeanAction",
                null, null);

        proxy.execute();

        // check if AOP works
        TestAspect aspectState = (TestAspect) appContext.getBean("test-aspect");
        // chainedAction.actionMethodName sets name then chainedAction.getCount sets count
        // then chaintoAction.setCount sets count2 then chainedAction.getName sets name again
        // then chaintoAction.actionMethodName sets issueId of the aspect object.
        assertEquals("setName(WW-4105)-setCount(1)-setCount2(1)-setName(WW-4105)-setIssueId(WW-4105)-", aspectState.log);
        assertEquals(aspectState.getName(), aspectState.getIssueId());
        assertEquals("WW-4105", aspectState.getIssueId());
        assertEquals(aspectState.getCount(), aspectState.getCount2());
        assertEquals(1, aspectState.getCount());

        // check if chain works
        TestSubBean chaintoAOPedAction = (TestSubBean) appContext.getBean("pointcutted-test-sub-bean");
        assertEquals(1, chaintoAOPedAction.getCount());
        assertEquals("WW-4105", chaintoAOPedAction.getName());
    }
