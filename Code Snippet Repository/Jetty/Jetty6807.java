    @Test
    public void testAnnotatedNoop()
    {
        JettyAnnotatedScanner impl = new JettyAnnotatedScanner();
        JettyAnnotatedMetadata metadata = impl.scan(NoopSocket.class);

        String classId = NoopSocket.class.getSimpleName();

        Assert.assertThat("Methods for " + classId,metadata,notNullValue());

        assertNoEventMethod(classId + ".onBinary",metadata.onBinary);
        assertNoEventMethod(classId + ".onClose",metadata.onClose);
        assertNoEventMethod(classId + ".onConnect",metadata.onConnect);
        assertNoEventMethod(classId + ".onException",metadata.onError);
        assertNoEventMethod(classId + ".onText",metadata.onText);
        assertNoEventMethod(classId + ".onFrame",metadata.onFrame);
    }
