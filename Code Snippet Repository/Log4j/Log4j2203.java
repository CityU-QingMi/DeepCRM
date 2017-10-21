    private void checkMapEntry(final String key, final String value, final boolean compact, final String str,
            final boolean contextMapAslist) {
        this.toPropertySeparator(compact);
        if (contextMapAslist) {
            // {"key":"KEY", "value":"VALUE"}
            final String expected = String.format("{\"key\":\"%s\",\"value\":\"%s\"}", key, value);
            assertTrue("Cannot find contextMapAslist " + expected + " in " + str, str.contains(expected));
        } else {
            // "KEY":"VALUE"
            final String expected = String.format("\"%s\":\"%s\"", key, value);
            assertTrue("Cannot find contextMap " + expected + " in " + str, str.contains(expected));
        }
    }
