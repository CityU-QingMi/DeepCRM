    @Override
    public Array fromString(String string) {
        if ( string == null || string.isEmpty() ) {
            return null;
        }
        String[] tokens = string.split( DELIMITER );
        Array array = new Array();
        array.addAll( Arrays.asList(tokens) );
        return array;
    }
