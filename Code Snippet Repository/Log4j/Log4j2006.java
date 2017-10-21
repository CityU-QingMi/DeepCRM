    @Test
    public void testAdvertisementsRemovedOnConfigStop() {
        verifyExpectedEntriesAdvertised(InMemoryAdvertiser.getAdvertisedEntries());

        final LoggerContext ctx = LoggerContext.getContext();
        ctx.stop();

        final Map<Object, Map<String, String>> entries = InMemoryAdvertiser.getAdvertisedEntries();
        assertTrue("Entries found: " + entries, entries.isEmpty());

        //reconfigure for subsequent testing
        ctx.start();
    }
