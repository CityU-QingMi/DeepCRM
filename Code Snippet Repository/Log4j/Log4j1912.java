    @Test
    public void testAcceptCallsNestedConditionsOnlyIfPathAccepted() {
        final CountingCondition counter = new CountingCondition(true);
        final IfAccumulatedFileSize condition = IfAccumulatedFileSize.createFileSizeCondition("2KB", counter);
        final DummyFileAttributes attribs = new DummyFileAttributes();

        final long quarter = condition.getThresholdBytes() / 4;
        attribs.size = quarter;
        assertFalse(condition.accept(null, null, attribs));
        assertEquals(0, counter.getAcceptCount());

        assertFalse(condition.accept(null, null, attribs));
        assertEquals(0, counter.getAcceptCount());

        assertFalse(condition.accept(null, null, attribs));
        assertEquals(0, counter.getAcceptCount());

        assertFalse(condition.accept(null, null, attribs));
        assertEquals(0, counter.getAcceptCount());

        assertTrue(condition.accept(null, null, attribs));
        assertEquals(1, counter.getAcceptCount());

        assertTrue(condition.accept(null, null, attribs));
        assertEquals(2, counter.getAcceptCount());
    }
