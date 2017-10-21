    @Override
    public void recycle(Deflater deflater)
    {
        if (_deflater.get()==null)
        {
            deflater.reset();
            _deflater.set(deflater);
        }
        else
            deflater.end();
    }
