    @Override
    public ThrowableProxy getThrownProxy() {
        // lazily instantiate the (expensive) ThrowableProxy
        if (thrownProxy == null) {
            if (thrown != null) {
                thrownProxy = new ThrowableProxy(thrown);
            }
        }
        return this.thrownProxy;
    }
