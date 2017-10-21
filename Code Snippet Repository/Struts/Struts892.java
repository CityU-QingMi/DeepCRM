    public void allocate( int initial, int limit  ) {
        if( buff==null || buff.length < initial ) {
            buff=new char[initial];
        }
        this.limit=limit;
        start=0;
        end=0;
        isSet=true;
        hasHashCode = false;
    }
