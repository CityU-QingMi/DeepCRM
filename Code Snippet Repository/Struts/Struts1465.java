    public void testIndexedList() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("beanList(1234567890).name", "This is the bla bean");
        params.put("beanList(1234567891).name", "This is the 2nd bla bean");

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        try {
            ActionProxy proxy = actionProxyFactory.createActionProxy("", "MyBean", null, extraContext);
            proxy.execute();
            assertEquals(2, Integer.parseInt(proxy.getInvocation().getStack().findValue("beanList.size").toString()));
            assertEquals(MyBean.class.getName(), proxy.getInvocation().getStack().findValue("beanList.get(0)").getClass().getName());
            assertEquals(MyBean.class.getName(), proxy.getInvocation().getStack().findValue("beanList.get(1)").getClass().getName());

            assertEquals("This is the bla bean", proxy.getInvocation().getStack().findValue("beanList.get(0).name"));
            assertEquals(new Long(1234567890), Long.valueOf(proxy.getInvocation().getStack().findValue("beanList.get(0).id").toString()));
            assertEquals("This is the 2nd bla bean", proxy.getInvocation().getStack().findValue("beanList.get(1).name"));
            assertEquals(new Long(1234567891), Long.valueOf(proxy.getInvocation().getStack().findValue("beanList.get(1).id").toString()));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
