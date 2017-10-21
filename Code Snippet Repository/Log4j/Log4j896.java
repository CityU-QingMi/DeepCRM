    private void setMessage(final Message msg) {
        if (msg instanceof ReusableMessage) {
            final ReusableMessage reusable = (ReusableMessage) msg;
            reusable.formatTo(getMessageTextForWriting());
            if (parameters != null) {
                parameters = reusable.swapParameters(parameters);
                parameterCount = reusable.getParameterCount();
            }
        } else {
            this.message = InternalAsyncUtil.makeMessageImmutable(msg);
        }
    }
