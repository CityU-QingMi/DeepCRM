  @Test
  public void testFallThroughToInsert() throws SQLException, LockException {
    UpdateLockRowSemaphore semaphore = new UpdateLockRowSemaphore();
    semaphore.setSchedName("test");

    Connection mockConnection = mock(Connection.class);
    when(mockConnection.prepareStatement(startsWith("UPDATE")))
            .thenReturn(FAIL_STATEMENT)
            .thenThrow(AssertionError.class);
    when(mockConnection.prepareStatement(startsWith("INSERT")))
            .thenReturn(GOOD_STATEMENT)
            .thenThrow(AssertionError.class);
    
    Assert.assertTrue(semaphore.obtainLock(mockConnection, "test"));
  }
