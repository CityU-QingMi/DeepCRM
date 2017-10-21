  @Override
  protected String createClassPath(Class client) {
    List<String> toolkitRuntime = TestBaseUtil.getToolkitRuntimeDependencies(ToolkitFactory.class);
    String test = TestBaseUtil.jarFor(client);
    String quartz = TestBaseUtil.jarFor(StdSchedulerFactory.class);
    String quartzJobs = TestBaseUtil.jarFor(MyJob.class);
    String logging = TestBaseUtil.jarFor(org.slf4j.LoggerFactory.class);
    String binder = TestBaseUtil.jarFor(org.slf4j.impl.StaticLoggerBinder.class);
    String log4j = TestBaseUtil.jarFor(org.apache.log4j.Level.class);
    String junit = TestBaseUtil.jarFor(org.junit.Assert.class);
    String mockito = TestBaseUtil.jarFor(org.hamcrest.core.Is.class);

    return makeClasspath(toolkitRuntime, test, quartz, quartzJobs, logging, binder, log4j, junit, mockito);
  }
