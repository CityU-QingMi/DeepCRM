    @Test
    public void testDoSFilterJMX() throws Exception
    {
        Server server = new Server();
        Connector connector = new ServerConnector(server);
        server.addConnector(connector);

        ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        DoSFilter filter = new DoSFilter();
        FilterHolder holder = new FilterHolder(filter);
        String name = "dos";
        holder.setName(name);
        holder.setInitParameter(DoSFilter.MANAGED_ATTR_INIT_PARAM, "true");
        context.addFilter(holder, "/*", EnumSet.of(DispatcherType.REQUEST));
        context.setInitParameter(ServletContextHandler.MANAGED_ATTRIBUTES, name);

        MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
        MBeanContainer mbeanContainer = new MBeanContainer(mbeanServer);
        server.addBean(mbeanContainer);

        server.start();

        String domain = DoSFilter.class.getPackage().getName();
        Set<ObjectName> mbeanNames = mbeanServer.queryNames(ObjectName.getInstance(domain + ":*"), null);
        Assert.assertEquals(1, mbeanNames.size());
        ObjectName objectName = mbeanNames.iterator().next();

        boolean value = (Boolean)mbeanServer.getAttribute(objectName, "enabled");
        mbeanServer.setAttribute(objectName, new Attribute("enabled", !value));
        Assert.assertEquals(!value, filter.isEnabled());

        String whitelist = (String)mbeanServer.getAttribute(objectName, "whitelist");
        String address = "127.0.0.1";
        Assert.assertFalse(whitelist.contains(address));
        boolean result = (Boolean)mbeanServer.invoke(objectName, "addWhitelistAddress", new Object[]{address}, new String[]{String.class.getName()});
        Assert.assertTrue(result);
        whitelist = (String)mbeanServer.getAttribute(objectName, "whitelist");
        Assert.assertTrue(whitelist.contains(address));

        result = (Boolean)mbeanServer.invoke(objectName, "removeWhitelistAddress", new Object[]{address}, new String[]{String.class.getName()});
        Assert.assertTrue(result);
        whitelist = (String)mbeanServer.getAttribute(objectName, "whitelist");
        Assert.assertFalse(whitelist.contains(address));

        server.stop();
    }
