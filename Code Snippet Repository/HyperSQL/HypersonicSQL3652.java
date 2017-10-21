    protected DTIType(int typeGroup, int type, long precision, int scale,
                      int startIntervalType, int endIntervalType) {

        super(typeGroup, type, precision, scale);

        this.startIntervalType = startIntervalType;
        this.endIntervalType   = endIntervalType;
        startPartIndex         = intervalIndexMap.get(startIntervalType);
        endPartIndex           = intervalIndexMap.get(endIntervalType);
    }
