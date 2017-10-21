  @Override
  protected final Scheduler setupScheduler() {
    // Set this to get it substituted in the context file
    System.setProperty("tcConfigUrl", getTerracottaUrl());

    // using an application context so that it will be set in the JobDetailBean instances
    context = new ClassPathXmlApplicationContext(contextFile, getClass());

    return (Scheduler) context.getBean("scheduler");

  }
