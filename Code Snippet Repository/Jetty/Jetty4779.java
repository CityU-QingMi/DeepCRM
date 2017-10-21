    private void keySet(Set<String> set, int t)
    {
        if (t<_value.length&&_value[t]!=null)
            set.add(_key[t]);

        for(int i=0; i < ROW_SIZE; i++)
        {
            int idx=t*ROW_SIZE+i;
            if (idx<_rowIndex.length && _rowIndex[idx] != 0)
                keySet(set,_rowIndex[idx]);
        }
        
        char[] big = _bigIndex==null||t>=_bigIndex.length?null:_bigIndex[t];
        if (big!=null)
        {
            for (int i:big)
                if (i!=0)
                    keySet(set,i);
        }
    }
