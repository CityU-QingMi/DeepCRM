    @Test
    public void testLog4j2() {

        // Argument-array creation
        final int limit = 37;
        final Object[] args = createArray(limit);
        final Object[] originalArgs = Arrays.copyOf(args, args.length);

        System.out.println("args " + Arrays.toString(args));

        // Logger definition
        final String someFormat = "test {}";
        final Logger logger = LoggerFactory.getLogger(this.getClass());

        // First logging of args
        logger.error(someFormat, args); // Only the first element (args[0]) of args will be logged - why?
        // GG: because the pattern {} picks up the 1 st argument, not the whole array
        Assert.assertArrayEquals(originalArgs, args);
        System.out.println("args are still ok: " + Arrays.toString(args));

        // Bug: The second logging of args sets all elements of args to null
        logger.error(someFormat, args);
        // GG: All is well args is still intact
        System.out.println("args " + Arrays.toString(args));
        Assert.assertArrayEquals(originalArgs, args);
    }
