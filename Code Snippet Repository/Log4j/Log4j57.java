    public static void assertStreamEquals(
        final String witness, final byte[] actual, final int[] skip,
        final int endCompare) throws IOException {
        final File witnessFile = new File(witness);

        if (witnessFile.exists()) {
            int skipIndex = 0;
            final byte[] expected = FileUtils.readFileToByteArray(witnessFile);
            final int bytesRead = expected.length;

            if (bytesRead < endCompare) {
                assertEquals(bytesRead, actual.length);
            }

            int endScan = actual.length;

            if (endScan > endCompare) {
                endScan = endCompare;
            }

            for (int i = 0; i < endScan; i++) {
                if ((skipIndex < skip.length) && (skip[skipIndex] == i)) {
                    skipIndex++;
                } else {
                    if (expected[i] != actual[i]) {
                        assertEquals(
                            "Difference at offset " + i, expected[i], actual[i]);
                    }
                }
            }
        } else {
            //
            //  if the file doesn't exist then
            //      assume that we are setting up and need to write it
            FileUtils.writeByteArrayToFile(witnessFile, actual);
            fail("Writing witness file " + witness);
        }
    }
