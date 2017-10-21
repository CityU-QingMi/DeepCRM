        @Override
        public synchronized void setAttribute(String name, Object value)
        {
            Object old_value = super.getAttribute(name);

            if (value == null)
                super.removeAttribute(name);
            else
                super.setAttribute(name,value);

            if (!_servletContextAttributeListeners.isEmpty())
            {
                ServletContextAttributeEvent event = new ServletContextAttributeEvent(_scontext,name,old_value == null?value:old_value);

                for (ServletContextAttributeListener l : _servletContextAttributeListeners)
                {
                    if (old_value == null)
                        l.attributeAdded(event);
                    else if (value == null)
                        l.attributeRemoved(event);
                    else
                        l.attributeReplaced(event);
                }
            }
        }
