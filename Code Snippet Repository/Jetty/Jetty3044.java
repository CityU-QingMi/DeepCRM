        @Override
        public synchronized void removeAttribute(String name)
        {
            Object old_value = super.getAttribute(name);
            super.removeAttribute(name);
            if (old_value != null &&!_servletContextAttributeListeners.isEmpty())
            {
                ServletContextAttributeEvent event = new ServletContextAttributeEvent(_scontext,name,old_value);

                for (ServletContextAttributeListener l : _servletContextAttributeListeners)
                    l.attributeRemoved(event);
            }
        }
