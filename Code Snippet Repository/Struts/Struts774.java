        @Override
        public Locale store(ActionInvocation invocation, Locale locale) {
            //save it in session
            Map<String, Object> session = invocation.getInvocationContext().getSession();

            if (session != null) {
                String sessionId = ServletActionContext.getRequest().getSession().getId();
                synchronized (sessionId.intern()) {
                    session.put(attributeName, locale);
                }
            }
            return locale;
        }
