    public void testIndexedMap() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("beanMap[1234567890].id", "1234567890");
        params.put("beanMap[1234567891].id", "1234567891");

        params.put("beanMap[1234567890].name", "This is the bla bean");
        params.put("beanMap[1234567891].name", "This is the 2nd bla bean");

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        try {
            ActionProxy proxy = actionProxyFactory.createActionProxy("", "MyBean", null, extraContext);
            proxy.execute();
            MyBeanAction action = (MyBeanAction) proxy.getInvocation().getAction();

            assertEquals(2, Integer.parseInt(proxy.getInvocation().getStack().findValue("beanMap.size").toString()));

            assertEquals(true, action.getBeanMap().containsKey(1234567890L));
            assertEquals(true, action.getBeanMap().containsKey(1234567891L));


            assertEquals(MyBean.class.getName(), proxy.getInvocation().getStack().findValue("beanMap.get(1234567890L)").getClass().getName());
            assertEquals(MyBean.class.getName(), proxy.getInvocation().getStack().findValue("beanMap.get(1234567891L)").getClass().getName());

            assertEquals("This is the bla bean", proxy.getInvocation().getStack().findValue("beanMap.get(1234567890L).name"));
            assertEquals("This is the 2nd bla bean", proxy.getInvocation().getStack().findValue("beanMap.get(1234567891L).name"));

            assertEquals("1234567890", proxy.getInvocation().getStack().findValue("beanMap.get(1234567890L).id").toString());
            assertEquals("1234567891", proxy.getInvocation().getStack().findValue("beanMap.get(1234567891L).id").toString());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
