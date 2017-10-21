    @Override
    public void onClose()
    {
        try
        {
            super.onClose();
        }
        finally
        {
            if (_selector!=null)
                _selector.destroyEndPoint(this);
        }
    }
