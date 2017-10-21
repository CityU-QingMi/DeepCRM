    public void dump()
    {
        for (int r=0;r<_rows;r++)
        {
            char c=_tree[r*ROW_SIZE+0];
            System.err.printf("%4d [%s,%d,%d,%d] '%s':%s%n",
                r,
                (c<' '||c>127)?(""+(int)c):"'"+c+"'",
                (int)_tree[r*ROW_SIZE+LO],
                (int)_tree[r*ROW_SIZE+EQ],
                (int)_tree[r*ROW_SIZE+HI],
                _key[r],
                _value[r]);
        }
        
    }
