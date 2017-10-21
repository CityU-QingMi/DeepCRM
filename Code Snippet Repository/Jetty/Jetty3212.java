    protected void callSessionAttributeListeners (String name, Object newValue, Object oldValue)
    {
        if (newValue==null || !newValue.equals(oldValue))
        {
            if (oldValue!=null)
                unbindValue(name,oldValue);
            if (newValue!=null)
                bindValue(name,newValue);

            if (_handler == null)
                throw new IllegalStateException ("No session manager for session "+ _sessionData.getId());
            
            _handler.doSessionAttributeListeners(this,name,oldValue,newValue);
        }
    }
