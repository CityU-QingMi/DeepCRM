    public static String toString(Location location) {
        StringBuilder result = new StringBuilder();

        String description = location.getDescription();
        if (description != null) {
            result.append(description).append(" - ");
        }

        String uri = location.getURI();
        if (uri != null) {
            result.append(uri).append(':').append(location.getLineNumber()).append(':').append(location.getColumnNumber());
        } else {
            result.append(UNKNOWN_STRING);
        }
        
        return result.toString();
    }
