    @Override
    public String toString()
    {
        int q;
        ByteBuffer b;
        String o;
        try(Locker.Lock lock = _locker.lock())
        {
            q=_inQ.size();
            b=_inQ.peek();
            o=BufferUtil.toDetailString(_out);
        }
        return String.format("%s[q=%d,q[0]=%s,o=%s]",super.toString(),q,b,o);
    }
