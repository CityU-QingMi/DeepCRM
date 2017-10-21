    private V getBest(int t,byte[] b, int offset, int len)
    {
        int node=t;
        int end=offset+len;
        loop: while(offset<end)
        {
            byte c=(byte)(b[offset++]&0x7f);
            len--;
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
                        V better=getBest(t,b,offset,len);
                        if (better!=null)
                            return better;
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
