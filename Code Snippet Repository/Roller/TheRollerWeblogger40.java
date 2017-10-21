    public static String formatIso8601(Date date) {
        if (date == null) {
            return "";
        }
        
        // Add a colon 2 chars before the end of the string
        // to make it a valid ISO-8601 date.
        
        String str = format(date, getIso8601DateFormat());
        StringBuilder sb = new StringBuilder();
        sb.append( str.substring(0,str.length()-2) );
        sb.append( ":" );
        sb.append( str.substring(str.length()-2) );
        return sb.toString();
    }
