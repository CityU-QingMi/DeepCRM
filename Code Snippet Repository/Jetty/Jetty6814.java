    @Test
    public void testAnnotatedBinaryArraySocket()
    {
        JettyAnnotatedScanner impl = new JettyAnnotatedScanner();
        JettyAnnotatedMetadata metadata = impl.scan(AnnotatedBinaryArraySocket.class);

        String classId = AnnotatedBinaryArraySocket.class.getSimpleName();

        Assert.assertThat("EventMethods for " + classId,metadata,notNullValue());

        assertHasEventMethod(classId + ".onBinary",metadata.onBinary);
        assertHasEventMethod(classId + ".onClose",metadata.onClose);
        assertHasEventMethod(classId + ".onConnect",metadata.onConnect);
        assertNoEventMethod(classId + ".onException",metadata.onError);
        assertNoEventMethod(classId + ".onText",metadata.onText);
        assertNoEventMethod(classId + ".onFrame",metadata.onFrame);

        Assert.assertFalse(classId + ".onBinary.isSessionAware",metadata.onBinary.isSessionAware());
        Assert.assertFalse(classId + ".onBinary.isStreaming",metadata.onBinary.isStreaming());
    }
