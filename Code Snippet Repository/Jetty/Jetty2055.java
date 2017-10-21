    @SuppressWarnings("")
    public final boolean match(long timestamp) 
        throws Exception
    {
        MBeanServerConnection serverConnection = JMXMonitor.getServiceConnection();

        TYPE value = null;
        try
        {
            int pos = _attributeName.indexOf('.');
            if (pos < 0)
                value = (TYPE)serverConnection.getAttribute(_nameObject,_attributeName);
            else
                value =  getValue((CompositeData)serverConnection.getAttribute(_nameObject, _attributeName.substring(0, pos)),
                                  _attributeName.substring(pos+1));
        }
        catch (Exception ex)
        {
            LOG.debug(ex);
        }

        boolean result = false;
        if (value != null)
        {
            result = match(value);
            
            if (result || getSaveAll())
            {
                _states.put(timestamp, 
                            new EventState<TYPE>(this.getID(), this.getNameString(), value));
            }
        }
            
        return result;
    }
