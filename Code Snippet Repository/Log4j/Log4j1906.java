    @Test
    public void testAcceptCallsNestedConditionsOnlyIfPathAccepted() {
        final CountingCondition counter = new CountingCondition(true);
        final IfAccumulatedFileCount condition = IfAccumulatedFileCount.createFileCountCondition(3, counter);

        for (int i = 1; i < 10; i++) {
            if (i <= 3) {
                assertFalse("i=" + i, condition.accept(null, null, null));
                assertEquals(0, counter.getAcceptCount());
            } else {
                assertTrue(condition.accept(null, null, null));
                assertEquals(i - 3, counter.getAcceptCount());
            }
        }
    }
