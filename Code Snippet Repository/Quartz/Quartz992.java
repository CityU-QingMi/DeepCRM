    @Override
    protected void configureWar(DeploymentBuilder builder) {
      builder.addDirectoryOrJARContainingClass(ToolkitFactory.class); // toolkit-runtime
      builder.addDirectoryOrJARContainingClass(StdSchedulerFactory.class); // core quartz
      builder.addDirectoryOrJARContainingClass(TerracottaJobStore.class); // quartz-terracotta
      builder.addDirectoryOrJARContainingClass(LoggerFactory.class); // sl4j-api
      builder.addDirectoryOrJARContainingClass(Log4jLoggerFactory.class); // sl4j-log4j12
      builder.addDirectoryOrJARContainingClass(Logger.class); // log4j

      builder.addFileAsResource(createConfigFile(), "WEB-INF/classes/");

      builder.addServlet("BasicTestServlet", "/BasicTestServlet/*", BasicTestServlet.class, null, false);
    }
