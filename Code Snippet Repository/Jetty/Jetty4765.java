    private V getBest(int t,String s,int offset,int len)
    {
        int node=t;
        int end=offset+len;
        loop: while(offset<end)
        {
            char c=s.charAt(offset++);
            len--;
            if(isCaseInsensitive() && c<128)
                c=StringUtil.lowercases[c];

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
                        V better=getBest(t,s,offset,len);
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
