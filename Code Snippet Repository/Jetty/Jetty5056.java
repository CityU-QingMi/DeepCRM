    private static <V> void toString(Appendable out, TreeTrie<V> t)
    {
        if (t != null)
        {
            if (t._value!=null)
            {
                try
                {
                    out.append(',');
                    out.append(t._key);
                    out.append('=');
                    out.append(t._value.toString());
                }
                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
            }
           
            for(int i=0; i < INDEX; i++)
            {
                if (t._nextIndex[i] != null)
                    toString(out,t._nextIndex[i]);
            }
            for (int i=t._nextOther.size();i-->0;)
                toString(out,t._nextOther.get(i));
        }           
    }
