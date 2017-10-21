    private void checkResult(final File file, final String loggerContextName) throws IOException {
        final String contextDesc = contextImpl + " " + contextImpl.implClassSimpleName() + " " + loggerContextName;
        try (final BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String expect;
            for (int i = 0; i < LINE_COUNT; i++) {
                final String line = reader.readLine();
                if ((i & 1) == 1) {
                    expect = "INFO c.f.Bar mapvalue [stackvalue] {KEY=mapvalue, configProp=configValue, configProp2=configValue2, count=" + i + "} "
                            + contextDesc + " i=" + i;
                } else {
                    expect = "INFO c.f.Bar mapvalue [stackvalue] {KEY=mapvalue, configProp=configValue, configProp2=configValue2} " + contextDesc + " i=" + i;
                }
                assertEquals(file.getName() + ": line " + i, expect, line);
            }
            final String noMoreLines = reader.readLine();
            assertNull("done", noMoreLines);
        } finally {
            file.delete();
        }
    }
