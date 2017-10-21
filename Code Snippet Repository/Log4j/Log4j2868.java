    @Setup
    public void setup() {
        System.setProperty("log4j2.threadContextMap", IMPLEMENTATIONS.get(threadContextMapAlias).getName());
        ThreadContextBenchmarkAccess.init();

        injector = ContextDataInjectorFactory.createInjector();
        System.out.println(threadContextMapAlias + ": Injector = " + injector);

        reusableContextData = threadContextMapAlias.contains("Array")
                ? new SortedArrayStringMap()
                : new OpenHashStringMap<>();

        keys = new String[count];
        values = new String[count];
        final Random r = new Random();
        for (int j = 0; j < keys.length; j++) {
            final char[] str = new char[KEY_LENGTH];
            for (int i = 0; i < str.length; i++) {
                str[i] = (char) r.nextInt();
            }
            keys[j] = new String(str);
            values[j] = new String(str);
        }
        final int PROPERTIES_COUNT = 5; // count
        propertyList = new ArrayList<>(PROPERTIES_COUNT);
        for (int j = 0; j < PROPERTIES_COUNT; j++) {
            final char[] str = new char[KEY_LENGTH];
            for (int i = 0; i < str.length; i++) {
                str[i] = (char) r.nextInt();
            }
            propertyList.add(Property.createProperty(new String(str), new String(str)));
        }

        clearAndPut(); // ensure ThreadContext contains values
    }
