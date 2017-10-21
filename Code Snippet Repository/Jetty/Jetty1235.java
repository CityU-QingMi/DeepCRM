        @Override
        public boolean onIdleTimeout(Session session)
        {
            boolean close = super.onIdleTimeout(session);
            if (!close)
                return false;

            long idleTimeout = getConnection().getEndPoint().getIdleTimeout();
            return getConnection().onSessionTimeout(new TimeoutException("Session idle timeout " + idleTimeout + " ms"));
        }
