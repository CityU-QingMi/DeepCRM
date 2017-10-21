    @Override
    public void setAttribute(String name, Object value)
    {
        Object old_value = _attributes == null?null:_attributes.getAttribute(name);

        if ("org.eclipse.jetty.server.Request.queryEncoding".equals(name))
            setQueryEncoding(value == null?null:value.toString());
        else if ("org.eclipse.jetty.server.sendContent".equals(name))
            LOG.warn("Deprecated: org.eclipse.jetty.server.sendContent");

        if (_attributes == null)
            _attributes = new AttributesMap();
        _attributes.setAttribute(name,value);

        if (!_requestAttributeListeners.isEmpty())
        {
            final ServletRequestAttributeEvent event = new ServletRequestAttributeEvent(_context,this,name,old_value == null?value:old_value);
            for (ServletRequestAttributeListener l : _requestAttributeListeners)
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
