    @Test
    public void testAnnotatedBinaryStreamSocket()
    {
        JettyAnnotatedScanner impl = new JettyAnnotatedScanner();
        JettyAnnotatedMetadata metadata = impl.scan(AnnotatedBinaryStreamSocket.class);

        String classId = AnnotatedBinaryStreamSocket.class.getSimpleName();

        Assert.assertThat("EventMethods for " + classId,metadata,notNullValue());

        assertHasEventMethod(classId + ".onBinary",metadata.onBinary);
        assertHasEventMethod(classId + ".onClose",metadata.onClose);
        assertHasEventMethod(classId + ".onConnect",metadata.onConnect);
        assertNoEventMethod(classId + ".onException",metadata.onError);
        assertNoEventMethod(classId + ".onText",metadata.onText);
        assertNoEventMethod(classId + ".onFrame",metadata.onFrame);

        Assert.assertFalse(classId + ".onBinary.isSessionAware",metadata.onBinary.isSessionAware());
        Assert.assertTrue(classId + ".onBinary.isStreaming",metadata.onBinary.isStreaming());
    }
