    public void makeSpace(int count)
    {
        char[] tmp = null;

        int newSize;
        int desiredSize=end + count;

        // Can't grow above the limit
        if( limit > 0 &&
            desiredSize > limit) {
            desiredSize=limit;
        }

        if( buff==null ) {
            if( desiredSize < 256 )
             {
                desiredSize=256; // take a minimum
            }
            buff=new char[desiredSize];
        }

        // limit < buf.length ( the buffer is already big )
        // or we already have space XXX
        if( desiredSize <= buff.length) {
            return;
        }
        // grow in larger chunks
        if( desiredSize < 2 * buff.length ) {
            newSize= buff.length * 2;
            if( limit >0 &&
                newSize > limit ) {
                newSize=limit;
            }
            tmp=new char[newSize];
        } else {
            newSize= buff.length * 2 + count ;
            if( limit > 0 &&
                newSize > limit ) {
                newSize=limit;
            }
            tmp=new char[newSize];
        }

        System.arraycopy(buff, 0, tmp, 0, end);
        buff = tmp;
        tmp = null;
    }
