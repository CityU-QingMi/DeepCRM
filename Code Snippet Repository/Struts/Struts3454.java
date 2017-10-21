    private void performProgrammaticValidation(ActionInvocation invocation, Object action) throws Exception {
        if (action instanceof Validateable && programmatic) {
            // keep exception that might occured in validateXXX or validateDoXXX
            Exception exception = null;

            Validateable validateable = (Validateable) action;
            LOG.debug("Invoking validate() on action [{}]", validateable);

            try {
                PrefixMethodInvocationUtil.invokePrefixMethod(
                        invocation,
                        new String[]{VALIDATE_PREFIX, ALT_VALIDATE_PREFIX});
            } catch (Exception e) {
                // If any exception occurred while doing reflection, we want
                // validate() to be executed
                LOG.warn("An exception occurred while executing the prefix method", e);
                exception = e;
            }

            if (alwaysInvokeValidate) {
                validateable.validate();
            }

            if (exception != null) {
                // rethrow if something is wrong while doing validateXXX / validateDoXXX
                throw exception;
            }
        }
    }
