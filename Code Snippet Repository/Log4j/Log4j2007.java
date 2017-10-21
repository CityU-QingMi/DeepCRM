    @Test
    public void testAdvertisementsAddedOnReconfigAfterStop() {
        verifyExpectedEntriesAdvertised(InMemoryAdvertiser.getAdvertisedEntries());

        final LoggerContext ctx = LoggerContext.getContext();
        ctx.stop();

        final Map<Object, Map<String, String>> entries = InMemoryAdvertiser.getAdvertisedEntries();
        assertTrue("Entries found: " + entries, entries.isEmpty());

        ctx.start();

        verifyExpectedEntriesAdvertised(InMemoryAdvertiser.getAdvertisedEntries());
    }
