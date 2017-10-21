    @Test
    public void testAnnotatedTextSocket()
    {
        JettyAnnotatedScanner impl = new JettyAnnotatedScanner();
        JettyAnnotatedMetadata metadata = impl.scan(AnnotatedTextSocket.class);

        String classId = AnnotatedTextSocket.class.getSimpleName();

        Assert.assertThat("EventMethods for " + classId,metadata,notNullValue());

        assertNoEventMethod(classId + ".onBinary",metadata.onBinary);
        assertHasEventMethod(classId + ".onClose",metadata.onClose);
        assertHasEventMethod(classId + ".onConnect",metadata.onConnect);
        assertHasEventMethod(classId + ".onException",metadata.onError);
        assertHasEventMethod(classId + ".onText",metadata.onText);
        assertNoEventMethod(classId + ".onFrame",metadata.onFrame);

        Assert.assertFalse(classId + ".onText.isSessionAware",metadata.onText.isSessionAware());
        Assert.assertFalse(classId + ".onText.isStreaming",metadata.onText.isStreaming());
    }
