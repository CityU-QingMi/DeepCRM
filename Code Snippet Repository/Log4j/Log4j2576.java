    @Test
    public void testTimeBaseUuid() {
        final UUID uuid = UuidUtil.getTimeBasedUuid();
        //final UUID uuid2 = UuidUtil.getTimeBasedUUID(); // unused
        final long current = (System.currentTimeMillis() * 10000) + NUM_100NS_INTERVALS_SINCE_UUID_EPOCH;
        final long time = uuid.timestamp();
        assertTrue("Incorrect time", current + 10000 - time > 0);
        final UUID[] uuids = new UUID[COUNT];
        final long start = System.nanoTime();
        for (int i=0; i < COUNT; ++i) {
            uuids[i] = UuidUtil.getTimeBasedUuid();
        }
        final long elapsed = System.nanoTime() - start;
        System.out.println("Elapsed for " + COUNT + " UUIDS = " + elapsed + " Average = " + elapsed / COUNT + " ns");
        int errors = 0;
        for (int i=0; i < COUNT; ++i) {
            for (int j=i+1; j < COUNT; ++j) {
                if (uuids[i].equals(uuids[j])) {
                    ++errors;
                    System.out.println("UUID " + i + " equals UUID " + j);
                }
            }
        }
        assertEquals(errors + " duplicate UUIDS", 0, errors);
        final int variant = uuid.variant();
        assertEquals("Incorrect variant. Expected 2 got " + variant, 2, variant);
        final int version = uuid.version();
        assertEquals("Incorrect version. Expected 1 got " + version, 1, version);
        final long node = uuid.node();
        assertTrue("Invalid node", node != 0);
    }
