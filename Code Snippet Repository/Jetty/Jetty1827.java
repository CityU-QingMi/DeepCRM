        private Map getDelegate(boolean create)
        {
            if (!create || delegate != null) return delegate;
            if (create)
            {
                delegate = new HashMap();
                if (isMandatory) delegate.put(MANDATORY_KEY, "true");
                if (authMethod != null) delegate.put(AUTH_METHOD_KEY, authMethod);
            }
            return delegate;
        }
