    private void forceRemap(final int linesPerIteration, final int iterations, final IPerfTestRunner runner) {
        final int LINESEP = System.lineSeparator().getBytes(Charset.defaultCharset()).length;
        final int bytesPerLine = 0 + IPerfTestRunner.THROUGHPUT_MSG.getBytes().length;
        final int bytesWritten = bytesPerLine * linesPerIteration * iterations;
        final int threshold = 1073741824; // magic number: defined in perf9MMapLocation.xml

        int todo = threshold - bytesWritten;
        if (todo <= 0) {
            return;
        }
        final byte[] filler = new byte[4096];
        Arrays.fill(filler, (byte) 'X');
        final String str = new String(filler, Charset.defaultCharset());
        do {
            runner.log(str);
        } while ((todo -= (4096 + LINESEP)) > 0);
    }
