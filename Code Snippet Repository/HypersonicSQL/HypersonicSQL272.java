    synchronized void reset(NumberSequence other) {

        name       = other.name;
        startValue = other.startValue;
        currValue  = other.currValue;
        lastValue  = other.lastValue;
        increment  = other.increment;
        dataType   = other.dataType;
        minValue   = other.minValue;
        maxValue   = other.maxValue;
        isCycle    = other.isCycle;
        isAlways   = other.isAlways;
    }
