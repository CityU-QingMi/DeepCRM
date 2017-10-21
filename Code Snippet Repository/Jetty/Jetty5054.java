    private V getBestByteBuffer(ByteBuffer b,int offset,int len)
    {
        TreeTrie<V> t = this;
        int pos=b.position()+offset;
        for(int i=0; i < len; i++)
        {
            byte c=b.get(pos++);
            int index=c>=0&&c<0x7f?__lookup[c]:-1;
            if (index>=0)
            {
                if (t._nextIndex[index] == null) 
                    break;
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
                    break;
                t=n;
            }
            
            // Is the next Trie is a match
            if (t._key!=null)
            {
                // Recurse so we can remember this possibility
                V best=t.getBest(b,offset+i+1,len-i-1);
                if (best!=null)
                    return best;
                break;
            }
        }
        return t._value;
    }
