    @Override
    public V get(String s, int offset, int len)
    {
        int t = 0;
        for(int i=0; i < len; i++)
        {
            char c=s.charAt(offset+i);
            int index=__lookup[c&0x7f];
            if (index>=0)
            {
                int idx=t*ROW_SIZE+index;
                t=_rowIndex[idx];
                if (t==0)
                    return null;
            }
            else
            {
                char[] big = _bigIndex==null?null:_bigIndex[t];
                if (big==null)
                    return null;
                t=big[c];
                if (t==0)
                    return null;
            }
        }
        return _value[t];
    }
