  @Test
  public void testDoubleFailureFollowedBySuccessUsingUpdate() throws LockException, SQLException {
    UpdateLockRowSemaphore semaphore = new UpdateLockRowSemaphore();
    semaphore.setSchedName("test");

    Connection mockConnection = mock(Connection.class);
    when(mockConnection.prepareStatement(startsWith("UPDATE")))
            .thenReturn(BAD_STATEMENT, BAD_STATEMENT)
            .thenThrow(AssertionError.class);
    
    try {
      semaphore.obtainLock(mockConnection, "test");
      Assert.fail();
    } catch (LockException e) {
      //expected
    }
  }
