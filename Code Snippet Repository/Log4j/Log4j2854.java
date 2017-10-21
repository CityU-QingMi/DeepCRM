    @Benchmark
    public Class<?>[] test11_getClassContextViaCallerClass() {
        // let's not benchmark LinkedList or anything here
        final Class<?>[] classes = new Class<?>[100];
        Class<?> clazz;
        for (int i = 0; null != (clazz = StackLocatorUtil.getCallerClass(i)); i++) {
            classes[i] = clazz;
        }
        return classes;
    }
