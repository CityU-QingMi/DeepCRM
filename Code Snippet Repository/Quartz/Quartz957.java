  public ExpressShutdownTest(TestConfig testConfig) {
    super(testConfig, ShutdownClient.class);

    // JDK 1.5 perm gen collection is not reliable enough
    if (Vm.isJRockit() || Vm.isHotSpot() && Vm.isJDK15()) {
      disableTest();
    }
    testConfig.getClientConfig().addExtraClientJvmArg("-XX:MaxPermSize=64M");
    testConfig.getClientConfig().addExtraClientJvmArg("-XX:+HeapDumpOnOutOfMemoryError");
    testConfig.getClientConfig().addExtraClientJvmArg("-XX:SoftRefLRUPolicyMSPerMB=0");
  }
