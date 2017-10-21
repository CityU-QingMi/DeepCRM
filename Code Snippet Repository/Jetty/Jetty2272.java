    public void release ()
    {
        try
        {
            InitialContext ic = new InitialContext();
            ic.unbind(_objectNameString);
            ic.unbind(_namingEntryNameString);
            this._namingEntryNameString=null;
            this._objectNameString=null;
        }
        catch (NamingException e)
        {
            __log.warn(e);
        }
    }
