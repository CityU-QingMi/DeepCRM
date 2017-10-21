    @Override
    public Marker convertToEntityAttribute(final String s) {
        if (Strings.isEmpty(s)) {
            return null;
        }

        final int bracket = s.indexOf("[");

        return bracket < 1 ? MarkerManager.getMarker(s) : MarkerManager.getMarker(s.substring(0, bracket));
    }
