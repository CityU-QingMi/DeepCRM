    private V getBest(int t, String s, int offset, int len)
    {
        int pos=offset;
        for(int i=0; i < len; i++)
        {
            char c=s.charAt(pos++);
            int index=__lookup[c&0x7f];
            if (index>=0)
            {
                int idx=t*ROW_SIZE+index;
                int nt=_rowIndex[idx];
                if (nt==0)
                    break;
                t=nt;
            }
            else
            {
                char[] big = _bigIndex==null?null:_bigIndex[t];
                if (big==null)
                    return null;
                int nt=big[c];
                if (nt==0)
                    break;
                t=nt;
            }
            
            // Is the next Trie is a match
            if (_key[t]!=null)
            {
                // Recurse so we can remember this possibility
                V best=getBest(t,s,offset+i+1,len-i-1);
                if (best!=null)
                    return best;
                return (V)_value[t];
            }
        }
        return (V)_value[t];
    }
