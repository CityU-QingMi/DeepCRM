        public void handle(Callback[] callbacks)
                throws UnsupportedCallbackException {
            boolean didSetName = false;
            boolean didSetPassword = false;
            for (Callback cb : callbacks)
                if (cb instanceof NameCallback) {
                    ((NameCallback) cb).setName(u);
                    didSetName = true;
                } else if (cb instanceof PasswordCallback) {
                    ((PasswordCallback) cb).setPassword(p);
                    didSetPassword = true;
                } else {
                    throw new UnsupportedCallbackException(cb,
                            "Unsupported Callback type: "
                            + cb.getClass().getName());
                }
            if (!didSetName)
                throw new IllegalStateException(
                        "Supplied Callbacks does not include a NameCallback");
            if (!didSetPassword)
                throw new IllegalStateException("Supplied Callbacks "
                        + "does not include a PasswordCallback");
        }
