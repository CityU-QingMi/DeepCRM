    @Test
    public void testFormatTimestamp() {
        assertEquals("0", GelfLayout.formatTimestamp(0L).toString());
        assertEquals("1.000", GelfLayout.formatTimestamp(1000L).toString());
        assertEquals("1.001", GelfLayout.formatTimestamp(1001L).toString());
        assertEquals("1.010", GelfLayout.formatTimestamp(1010L).toString());
        assertEquals("1.100", GelfLayout.formatTimestamp(1100L).toString());
        assertEquals("1458741206.653", GelfLayout.formatTimestamp(1458741206653L).toString());
        assertEquals("9223372036854775.807", GelfLayout.formatTimestamp(Long.MAX_VALUE).toString());
    }
