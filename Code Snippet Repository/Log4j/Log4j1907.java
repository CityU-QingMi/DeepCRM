    @Test
    public void testGetThresholdBytes() {
        assertEquals(2, create("2B").getThresholdBytes());
        assertEquals(3, create("3 B").getThresholdBytes());
        assertEquals(2 * 1024, create("2KB").getThresholdBytes());
        assertEquals(3 * 1024, create("3 KB").getThresholdBytes());
        assertEquals(2 * 1024 * 1024, create("2MB").getThresholdBytes());
        assertEquals(3 * 1024 * 1024, create("3 MB").getThresholdBytes());
        assertEquals(2L * 1024 * 1024 * 1024, create("2GB").getThresholdBytes());
        assertEquals(3L * 1024 * 1024 * 1024, create("3 GB").getThresholdBytes());
    }
