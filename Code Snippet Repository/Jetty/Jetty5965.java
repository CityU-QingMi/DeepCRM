        @Override
        public void checkListener(Class<? extends EventListener> listener) throws IllegalStateException
        {
            try
            {
                super.checkListener(listener);
            }
            catch (IllegalArgumentException e)
            {
                //not one of the standard servlet listeners, check our extended session listener types
                boolean ok = false;
                for (Class<?> l:SessionHandler.SESSION_LISTENER_TYPES)
                {
                    if (l.isAssignableFrom(listener))
                    {
                        ok = true;
                        break;
                    }
                }
                if (!ok)
                    throw new IllegalArgumentException("Inappropriate listener type "+listener.getName());
            }
        }
