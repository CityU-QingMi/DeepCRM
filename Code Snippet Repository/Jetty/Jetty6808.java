    @Test
    public void testAnnotatedOnFrame()
    {
        JettyAnnotatedScanner impl = new JettyAnnotatedScanner();
        JettyAnnotatedMetadata metadata = impl.scan(FrameSocket.class);

        String classId = FrameSocket.class.getSimpleName();

        Assert.assertThat("EventMethods for " + classId,metadata,notNullValue());

        assertNoEventMethod(classId + ".onBinary",metadata.onBinary);
        assertNoEventMethod(classId + ".onClose",metadata.onClose);
        assertNoEventMethod(classId + ".onConnect",metadata.onConnect);
        assertNoEventMethod(classId + ".onException",metadata.onError);
        assertNoEventMethod(classId + ".onText",metadata.onText);
        assertHasEventMethod(classId + ".onFrame",metadata.onFrame);
    }
