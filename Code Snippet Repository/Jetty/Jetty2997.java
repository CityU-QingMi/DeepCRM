    @Override
    public void setAccepting(boolean accepting)
    {
        super.setAccepting(accepting);
        if (getAcceptors()>0)
            return;
        
        try
        {
            if (accepting)
            {
                if (_acceptor.get()==null)
                {
                    Closeable acceptor = _manager.acceptor(_acceptChannel);
                    if (!_acceptor.compareAndSet(null,acceptor))
                        acceptor.close();
                }
            }
            else
            {
                Closeable acceptor = _acceptor.get();
                if (acceptor!=null && _acceptor.compareAndSet(acceptor,null))
                    acceptor.close();
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        } 
    }
