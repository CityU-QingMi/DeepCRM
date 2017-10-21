    @Override
    public void setBufferSize(int size)
    {
        if (isCommitted())
            throw new IllegalStateException("cannot set buffer size after response is in committed state");
        if (getContentCount() > 0)
            throw new IllegalStateException("cannot set buffer size after response has " + getContentCount() + " bytes already written");
        if (size < __MIN_BUFFER_SIZE)
            size = __MIN_BUFFER_SIZE;
        _out.setBufferSize(size);
    }
