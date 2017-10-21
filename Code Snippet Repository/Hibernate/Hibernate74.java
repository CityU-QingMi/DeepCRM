    @Override
    public Object poll() {
        int size = size();
        if(size > 0) {
            Object first = get(0);
            remove( 0 );
            return first;
        }
        throw new NoSuchElementException();
    }
