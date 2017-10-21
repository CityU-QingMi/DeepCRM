    @Override
    public V get(String s,int offset, int len)
    {
        int t = 0;
        for(int i=0; i < len;)
        {
            char c=s.charAt(offset+i++);
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
                        return null;
                    break;
                }

                t=_tree[row+hilo(diff)];
                if (t==0)
                    return null;
            }
        }
        
        return _value[t];
    }
