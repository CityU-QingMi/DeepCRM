  @Test
  public void testSingleSuccessUsingUpdate() throws LockException, SQLException {
    UpdateLockRowSemaphore semaphore = new UpdateLockRowSemaphore();
    semaphore.setSchedName("test");

    Connection mockConnection = mock(Connection.class);
    when(mockConnection.prepareStatement(startsWith("UPDATE")))
            .thenReturn(GOOD_STATEMENT)
            .thenThrow(AssertionError.class);
    
    Assert.assertTrue(semaphore.obtainLock(mockConnection, "test"));
  }
