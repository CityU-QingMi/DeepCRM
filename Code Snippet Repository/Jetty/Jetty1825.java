        public Object remove(Object key)
        {
            if (MANDATORY_KEY.equals(key))
            {
                throw new IllegalArgumentException("Mandatory not mutable");
            }
            if (AUTH_METHOD_KEY.equals(key))
            {
                String authMethod = this.authMethod;
                this.authMethod = null;
                if (delegate != null) delegate.remove(AUTH_METHOD_KEY);
                return authMethod;
            }
            if (delegate == null) return null;
            return delegate.remove(key);
        }
