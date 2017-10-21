    @Override
    public boolean put(String s, V v)
    {
        TreeTrie<V> t = this;
        int limit = s.length();
        for(int k=0; k < limit; k++)
        {
            char c=s.charAt(k);
            
            int index=c>=0&&c<0x7f?__lookup[c]:-1;
            if (index>=0)
            {
                if (t._nextIndex[index] == null)
                    t._nextIndex[index] = new TreeTrie<V>(c);
                t = t._nextIndex[index];
            }
            else
            {
                TreeTrie<V> n=null;
                for (int i=t._nextOther.size();i-->0;)
                {
                    n=t._nextOther.get(i);
                    if (n._c==c)
                        break;
                    n=null;
                }
                if (n==null)
                {
                    n=new TreeTrie<V>(c);
                    t._nextOther.add(n);
                }
                t=n;
            }
        }
        t._key=v==null?null:s;
        t._value = v;
        return true;
    }
