    @Override
    public void recycle()
    {
        super.recycle();
        _unknownExpectation = false;
        _expect100Continue = false;
        _expect102Processing = false;
        _metadata.recycle();
        _connection = null;
        _fields.clear();
        _upgrade = null;
        _trailers = null;
    }
