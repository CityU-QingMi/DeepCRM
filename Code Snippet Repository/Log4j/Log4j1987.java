    private static void runPerfTests(final String[] args, final List<Setup> tests) throws IOException,
            InterruptedException, FileNotFoundException {
        final String java = args.length > 0 ? args[0] : "java";
        final int repeat = args.length > 1 ? Integer.parseInt(args[1]) : 5;
        int x = 0;
        for (final Setup setup : tests) {
            System.out.print(setup.description());
            final ProcessBuilder pb = setup.throughputTest(java);
            pb.redirectErrorStream(true); // merge System.out and System.err
            final long t1 = System.nanoTime();
            final int count = setup.threadCount >= 4 ? 3 : repeat;
            runPerfTest(count, x++, setup, pb);
            System.out.printf(" took %.1f seconds%n", (System.nanoTime() - t1) / (1000.0 * 1000.0 * 1000.0));

            final FileReader reader = new FileReader(setup.temp);
            final CharBuffer buffer = CharBuffer.allocate(256 * 1024);
            reader.read(buffer);
            reader.close();
            setup.temp.delete();
            buffer.flip();

            final String raw = buffer.toString();
            System.out.print(raw);
            final Stats stats = new Stats(raw);
            System.out.println(stats);
            System.out.println("-----");
            setup.stats = stats;
        }
        new File("perftest.log").delete();
    }
