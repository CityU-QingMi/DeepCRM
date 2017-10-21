  protected AbstractStandaloneTest(TestConfig testConfig, Class<? extends AbstractClientBase>... c) {
    super(testConfig);
    testConfig.getL2Config().setOffHeapEnabled(true);
    testConfig.getL2Config().setMaxOffHeapDataSize(512);
    testConfig.getL2Config().setMaxHeap(1024);
    testConfig.getL2Config().setMinHeap(256);
    testConfig.getClientConfig().setClientClasses(c);
    if (isDisabled()) {
      disableTest();
    }
  }
