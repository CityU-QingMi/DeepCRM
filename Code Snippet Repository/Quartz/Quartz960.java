  public void testForNewMethod() {
    Class jobStoreInterface = JobStore.class;

    try {
      jobStoreInterface.getMethod("getEstimatedTimeToReleaseAndAcquireTrigger", Long.TYPE);
      throw new AssertionError(
                               "TerracottaJobStoreExtensions.getEstimatedTimeToReleaseAndAcquireTrigger() can now be removed and then this test case deleted");
    } catch (NoSuchMethodException e) {
      Banner.warnBanner("Method does not yet exist -- it should someday!");
      e.printStackTrace();
      return;
    }
  }
