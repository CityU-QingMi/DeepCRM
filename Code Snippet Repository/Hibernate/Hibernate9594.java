    @Override
    public String toString(Array value) {
        StringBuilder builder = new StringBuilder();
        for ( String token : value ) {
            if ( builder.length() > 0 ) {
                builder.append( DELIMITER );
            }
            builder.append( token );
        }
        return builder.toString();
    }
