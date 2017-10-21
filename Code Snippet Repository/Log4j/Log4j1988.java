    private static void runPerfTest(final int repeat, final int setupIndex, final Setup config, final ProcessBuilder pb)
            throws IOException, InterruptedException {
        for (int i = 0; i < repeat; i++) {
            System.out.print(" (" + (i + 1) + '/' + repeat + ")...");
            final Process process = pb.start();

            final boolean[] stop = {false};
            printProcessOutput(process, stop);
            process.waitFor();
            stop[0] = true;

            final File gc = new File("gc" + setupIndex + '_' + i + config.log4jConfig + ".log");
            if (gc.exists()) {
                gc.delete();
            }
            new File("gc.log").renameTo(gc);
        }
    }
