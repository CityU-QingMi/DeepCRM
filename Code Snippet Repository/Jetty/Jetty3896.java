        @Override
        public void setSessionTrackingModes(Set<SessionTrackingMode> sessionTrackingModes)
        {
            if (!isStarting())
                throw new IllegalStateException();
            if (!_enabled)
                throw new UnsupportedOperationException();


            if (_sessionHandler!=null)
                _sessionHandler.setSessionTrackingModes(sessionTrackingModes);
        }
