        @Override
        public SessionCookieConfig getSessionCookieConfig()
        {
            if (!_enabled)
                throw new UnsupportedOperationException();

            if (_sessionHandler!=null)
                return _sessionHandler.getSessionCookieConfig();
            return null;
        }
