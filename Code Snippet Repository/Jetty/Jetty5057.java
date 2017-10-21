    private static <V> void keySet(Set<String> set, TreeTrie<V> t)
    {
        if (t != null)
        {
            if (t._key!=null)
                set.add(t._key);
           
            for(int i=0; i < INDEX; i++)
            {
                if (t._nextIndex[i] != null)
                    keySet(set,t._nextIndex[i]);
            }
            for (int i=t._nextOther.size();i-->0;)
                keySet(set,t._nextOther.get(i));
        }           
    }
