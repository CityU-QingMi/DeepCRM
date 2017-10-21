        public Object put(Object key, Object value)
        {
            if (MANDATORY_KEY.equals(key))
            {
                throw new IllegalArgumentException("Mandatory not mutable");
            }
            if (AUTH_METHOD_KEY.equals(key))
            {
                String authMethod = this.authMethod;
                this.authMethod = (String) value;
                if (delegate != null) delegate.put(AUTH_METHOD_KEY, value);
                return authMethod;
            }

            return getDelegate(true).put(key, value);
        }
