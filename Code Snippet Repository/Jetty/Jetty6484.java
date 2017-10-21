    private static void usage(String[] args)
    {
        System.err.println("ERROR: " + Arrays.asList(args));
        System.err.println("USAGE: java -cp CLASSPATH " + TestClient.class + " [ OPTIONS ]");
        System.err.println("  -h|--host HOST  (default localhost)");
        System.err.println("  -p|--port PORT  (default 8080)");
        System.err.println("  -b|--binary");
        System.err.println("  -v|--verbose");
        System.err.println("  -c|--count n    (default 10)");
        System.err.println("  -s|--size n     (default 64)");
        System.err.println("  -f|--fragment n (default 4000) ");
        System.err.println("  -P|--protocol echo|echo-assemble|echo-fragment|echo-broadcast");
        System.err.println("  -C|--clients n  (default 1) ");
        System.err.println("  -d|--delay n    (default 1000ms) ");
        System.exit(1);
    }
