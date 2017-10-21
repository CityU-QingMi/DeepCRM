        public void checkListener (Class<? extends EventListener> listener) throws IllegalStateException
        {
            boolean ok = false;
            int startIndex = (isExtendedListenerTypes()?EXTENDED_LISTENER_TYPE_INDEX:DEFAULT_LISTENER_TYPE_INDEX);
            for (int i=startIndex;i<SERVLET_LISTENER_TYPES.length;i++)
            {
                if (SERVLET_LISTENER_TYPES[i].isAssignableFrom(listener))
                {
                    ok = true;
                    break;
                }
            }
            if (!ok)
                throw new IllegalArgumentException("Inappropriate listener class "+listener.getName());
        }
