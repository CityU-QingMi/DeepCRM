  @Override
  protected final void test(Scheduler scheduler) throws Throwable {

    try {
      test0(scheduler);
    } finally {
      if (context.containsBean("taskExecutor")) {
        DisposableBean exec = (DisposableBean) context.getBean("taskExecutor");
        exec.destroy();
      }
    }
  }
