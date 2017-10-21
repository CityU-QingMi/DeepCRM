    @Test
    public void testAnnotatedMyEchoBinarySocket()
    {
        JettyAnnotatedScanner impl = new JettyAnnotatedScanner();
        JettyAnnotatedMetadata metadata = impl.scan(MyEchoBinarySocket.class);

        String classId = MyEchoBinarySocket.class.getSimpleName();

        Assert.assertThat("EventMethods for " + classId,metadata,notNullValue());

        assertHasEventMethod(classId + ".onBinary",metadata.onBinary);
        assertHasEventMethod(classId + ".onClose",metadata.onClose);
        assertHasEventMethod(classId + ".onConnect",metadata.onConnect);
        assertNoEventMethod(classId + ".onException",metadata.onError);
        assertHasEventMethod(classId + ".onText",metadata.onText);
        assertNoEventMethod(classId + ".onFrame",metadata.onFrame);
    }
