    private V getBest(int t,byte[] b,int offset,int len)
    {
        for(int i=0; i < len; i++)
        {
            byte c=b[offset+i];
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
                V best=getBest(t,b,offset+i+1,len-i-1);
                if (best!=null)
                    return best;
                break;
            }
        }
        return (V)_value[t];
    }
