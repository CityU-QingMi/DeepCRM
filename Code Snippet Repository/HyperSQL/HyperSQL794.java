    private Type translateType(Type type) {

        if (this.translateTTIType) {
            if (type.isIntervalType()) {
                type = ((IntervalType) type).getCharacterType();
            }
        }

        return type;
    }
