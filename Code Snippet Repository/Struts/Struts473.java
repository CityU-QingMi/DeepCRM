    public static LocationImpl parse(String text) throws IllegalArgumentException {
        if (text == null || text.length() == 0) {
            return null;
        }

        // Do we have a description?
        String description;
        int uriStart = text.lastIndexOf(" - "); // lastIndexOf to allow the separator to be in the description
        if (uriStart > -1) {
            description = text.substring(0, uriStart);
            uriStart += 3; // strip " - "
        } else {
            description = null;
            uriStart = 0;
        }
        
        try {
            int colSep = text.lastIndexOf(':');
            if (colSep > -1) {
                int column = Integer.parseInt(text.substring(colSep + 1));
                
                int lineSep = text.lastIndexOf(':', colSep - 1);
                if (lineSep > -1) {
                    int line = Integer.parseInt(text.substring(lineSep + 1, colSep));
                    return new LocationImpl(description, text.substring(uriStart, lineSep), line, column);
                }
            } else {
                // unkonwn?
                if (text.endsWith(UNKNOWN_STRING)) {
                    return LocationImpl.UNKNOWN;
                }
            }
        } catch(Exception e) {
            // Ignore: handled below
        }
        
        return LocationImpl.UNKNOWN;
    }
