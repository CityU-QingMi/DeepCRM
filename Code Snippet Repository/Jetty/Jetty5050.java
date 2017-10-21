    @Override
    public V get(String s,int offset, int len)
    {
        TreeTrie<V> t = this;
        for(int i=0; i < len; i++)
        {
            char c=s.charAt(offset+i);
            int index=c>=0&&c<0x7f?__lookup[c]:-1;
            if (index>=0)
            {
                if (t._nextIndex[index] == null) 
                    return null;
                t = t._nextIndex[index];
            }
            else
            {
                TreeTrie<V> n=null;
                for (int j=t._nextOther.size();j-->0;)
                {
                    n=t._nextOther.get(j);
                    if (n._c==c)
                        break;
                    n=null;
                }
                if (n==null)
                    return null;
                t=n;
            }
        }
        return t._value;
    }
