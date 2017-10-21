    @Override
    public V get(ByteBuffer b, int offset, int len)
    {
        int t = 0;
        offset+=b.position();
        
        for(int i=0; i < len;)
        {
            byte c=(byte)(b.get(offset+i++)&0x7f);
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
                        return null;
                    break;
                }

                t=_tree[row+hilo(diff)];
                if (t==0)
                    return null;
            }
        }

        return (V)_value[t];
    }
