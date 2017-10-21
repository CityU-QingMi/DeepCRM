    @Test
    public void testAnnotatedMyStatelessEchoSocket()
    {
        JettyAnnotatedScanner impl = new JettyAnnotatedScanner();
        JettyAnnotatedMetadata metadata = impl.scan(MyStatelessEchoSocket.class);

        String classId = MyStatelessEchoSocket.class.getSimpleName();

        Assert.assertThat("EventMethods for " + classId,metadata,notNullValue());

        assertNoEventMethod(classId + ".onBinary",metadata.onBinary);
        assertNoEventMethod(classId + ".onClose",metadata.onClose);
        assertNoEventMethod(classId + ".onConnect",metadata.onConnect);
        assertNoEventMethod(classId + ".onException",metadata.onError);
        assertHasEventMethod(classId + ".onText",metadata.onText);
        assertNoEventMethod(classId + ".onFrame",metadata.onFrame);

        Assert.assertTrue(classId + ".onText.isSessionAware",metadata.onText.isSessionAware());
        Assert.assertFalse(classId + ".onText.isStreaming",metadata.onText.isStreaming());
    }
