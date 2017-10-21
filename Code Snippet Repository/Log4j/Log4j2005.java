    private void verifyExpectedEntriesAdvertised(final Map<Object, Map<String, String>> entries) {
        boolean foundFile1 = false;
        boolean foundFile2 = false;
        boolean foundSocket1 = false;
        boolean foundSocket2 = false;
        for (final Map<String, String>entry:entries.values()) {
            if (foundFile1 && foundFile2 && foundSocket1 && foundSocket2) {
               break;
            }
            if (entry.get("name").equals("File1")) {
                foundFile1 = true;
            }
            if (entry.get("name").equals("File2")) {
                foundFile2 = true;
            }
            if (entry.get("name").equals("Socket1")) {
                foundSocket1 = true;
            }
            if (entry.get("name").equals("Socket2")) {
                foundSocket2 = true;
            }
        }
        assertTrue("Entries for File1 appender do not exist", foundFile1);
        assertTrue("Entries for File2 appender exist", !foundFile2);
        assertTrue("Entries for Socket1 appender do not exist", foundSocket1);
        assertTrue("Entries for Socket2 appender exist", !foundSocket2);
    }
