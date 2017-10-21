    @Override
    public void setupModule(final SetupContext context) {
        // Calling super is a MUST!
        super.setupModule(context);
        if (encodeThreadContextAsList) {
            new SetupContextInitializer().setupModule(context, includeStacktrace, stacktraceAsString);
        } else {
            new SetupContextJsonInitializer().setupModule(context, includeStacktrace, stacktraceAsString);
        }
    }
