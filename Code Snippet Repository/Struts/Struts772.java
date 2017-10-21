        @Override
        public Locale read(ActionInvocation invocation) {
            LOG.debug("Searching current Invocation context");
            // no overriding locale definition found, stay with current invocation (=browser) locale
            Locale locale = invocation.getInvocationContext().getLocale();
            if (locale != null) {
                LOG.debug("Applied invocation context locale: {}", locale);
            }
            return locale;
        }
