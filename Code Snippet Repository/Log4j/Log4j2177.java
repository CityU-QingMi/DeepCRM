    @Test
    public void testStackWithUnloadableClass() throws Exception {
        final Stack<Class<?>> stack = new Stack<>();
        final Map<String, ThrowableProxy.CacheEntry> map = new HashMap<>();

        final String runtimeExceptionThrownAtUnloadableClass_base64 = "rO0ABXNyABpqYXZhLmxhbmcuUnVudGltZUV4Y2VwdGlvbp5fBkcKNIPlAgAAeHIAE2phdmEubGFuZy5FeGNlcHRpb27Q/R8+GjscxAIAAHhyABNqYXZhLmxhbmcuVGhyb3dhYmxl1cY1Jzl3uMsDAANMAAVjYXVzZXQAFUxqYXZhL2xhbmcvVGhyb3dhYmxlO0wADWRldGFpbE1lc3NhZ2V0ABJMamF2YS9sYW5nL1N0cmluZztbAApzdGFja1RyYWNldAAeW0xqYXZhL2xhbmcvU3RhY2tUcmFjZUVsZW1lbnQ7eHBxAH4ABnB1cgAeW0xqYXZhLmxhbmcuU3RhY2tUcmFjZUVsZW1lbnQ7AkYqPDz9IjkCAAB4cAAAAAFzcgAbamF2YS5sYW5nLlN0YWNrVHJhY2VFbGVtZW50YQnFmiY23YUCAARJAApsaW5lTnVtYmVyTAAOZGVjbGFyaW5nQ2xhc3NxAH4ABEwACGZpbGVOYW1lcQB+AARMAAptZXRob2ROYW1lcQB+AAR4cAAAAAZ0ADxvcmcuYXBhY2hlLmxvZ2dpbmcubG9nNGouY29yZS5pbXBsLkZvcmNlTm9EZWZDbGFzc0ZvdW5kRXJyb3J0AB5Gb3JjZU5vRGVmQ2xhc3NGb3VuZEVycm9yLmphdmF0AARtYWlueA==";
        final byte[] binaryDecoded = Base64Converter
                .parseBase64Binary(runtimeExceptionThrownAtUnloadableClass_base64);
        final ByteArrayInputStream inArr = new ByteArrayInputStream(binaryDecoded);
        final ObjectInputStream in = new ObjectInputStream(inArr);
        final Throwable throwable = (Throwable) in.readObject();
        final ThrowableProxy subject = new ThrowableProxy(throwable);

        subject.toExtendedStackTrace(stack, map, null, throwable.getStackTrace());
    }
