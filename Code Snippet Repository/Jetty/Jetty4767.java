    private V getBest(int t,ByteBuffer b, int offset, int len)
    {
        int node=t;
        int o= offset+b.position();
        
        loop: for(int i=0; i<len; i++)
        {
            byte c=(byte)(b.get(o+i)&0x7f);
            if(isCaseInsensitive())
                c=(byte)StringUtil.lowercases[c];

            while (true)
            {
                int row = ROW_SIZE*t;
                char n=_tree[row];
                int diff=n-c;
                
                if (diff==0)
                {
                    t=_tree[row+EQ];
                    if (t==0)
                        break loop;
                    
                    // if this node is a match, recurse to remember 
                    if (_key[t]!=null)
                    {
                        node=t;
                        V best=getBest(t,b,offset+i+1,len-i-1);
                        if (best!=null)
                            return best;
                    }
                    break;
                }

                t=_tree[row+hilo(diff)];
                if (t==0)
                    break loop;
            }
        }
        return (V)_value[node];
    }
