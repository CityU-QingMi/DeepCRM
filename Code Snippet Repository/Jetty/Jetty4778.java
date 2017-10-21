    private void toString(Appendable out, int t)
    {
        if (_value[t]!=null)
        {
            try
            {
                out.append(',');
                out.append(_key[t]);
                out.append('=');
                out.append(_value[t].toString());
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }

        for(int i=0; i < ROW_SIZE; i++)
        {
            int idx=t*ROW_SIZE+i;
            if (_rowIndex[idx] != 0)
                toString(out,_rowIndex[idx]);
        }

        char[] big = _bigIndex==null?null:_bigIndex[t];
        if (big!=null)
        {
            for (int i:big)
                if (i!=0)
                    toString(out,i);
        }

    }
