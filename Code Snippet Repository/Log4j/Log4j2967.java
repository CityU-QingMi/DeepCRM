    @Test
    public void testException() {
        assertNull("The exception should be null (1).", this.tag.getException());

        Exception e = new Exception();
        this.tag.setException(e);
        assertSame("The exception is not correct (1).", e, this.tag.getException());

        this.tag.init();
        assertNull("The exception should be null (2).", this.tag.getException());

        e = new RuntimeException();
        this.tag.setException(e);
        assertSame("The exception is not correct (2).", e, this.tag.getException());
    }
