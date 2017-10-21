    @Test
    public void testAnnotatedMyEchoSocket()
    {
        JettyAnnotatedScanner impl = new JettyAnnotatedScanner();
        JettyAnnotatedMetadata metadata = impl.scan(MyEchoSocket.class);

        String classId = MyEchoSocket.class.getSimpleName();

        Assert.assertThat("EventMethods for " + classId,metadata,notNullValue());

        assertNoEventMethod(classId + ".onBinary",metadata.onBinary);
        assertHasEventMethod(classId + ".onClose",metadata.onClose);
        assertHasEventMethod(classId + ".onConnect",metadata.onConnect);
        assertNoEventMethod(classId + ".onException",metadata.onError);
        assertHasEventMethod(classId + ".onText",metadata.onText);
        assertNoEventMethod(classId + ".onFrame",metadata.onFrame);
    }
