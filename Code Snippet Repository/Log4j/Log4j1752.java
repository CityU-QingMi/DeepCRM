    protected void validate(final SyslogAppender syslogAppender) {
        final Layout<? extends Serializable> layout = syslogAppender.getLayout();
        if (layout instanceof SyslogLayout) {
            validate((SyslogLayout) layout);
        } else if (layout instanceof Rfc5424Layout) {
            validate((Rfc5424Layout) layout);
        } else {
            Assert.fail("Unexpected layout: " + layout);
        }
    }
