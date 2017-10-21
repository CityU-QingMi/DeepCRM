        @Override
        public Locale read(ActionInvocation invocation) {
            Locale locale = null;

            LOG.debug("Checks session for saved locale");
            Map<String, Object> session = invocation.getInvocationContext().getSession();

            if (session != null) {
                //[WW-4741] Do not force session creation while this is a read operation
                HttpSession httpSession = ServletActionContext.getRequest().getSession(false);
                if(null != httpSession) {
                    String sessionId = httpSession.getId();
                    synchronized (sessionId.intern()) {
                        Object sessionLocale = session.get(attributeName);
                        if (sessionLocale != null && sessionLocale instanceof Locale) {
                            locale = (Locale) sessionLocale;
                            LOG.debug("Applied session locale: {}", locale);
                        }
                    }
                }
            }

            if (locale == null) {
                LOG.debug("No Locale defined in session, fetching from current request and it won't be stored in session!");
                shouldStore = false;
                locale = super.read(invocation);
            } else {
                LOG.debug("Found stored Locale {} in session, using it!", locale);
            }

            return locale;
        }
