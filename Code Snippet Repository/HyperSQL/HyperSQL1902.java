    synchronized NumberSequence duplicate() {

        NumberSequence copy = new NumberSequence(name, dataType);

        copy.startValue = startValue;
        copy.currValue  = currValue;
        copy.lastValue  = lastValue;
        copy.increment  = increment;
        copy.minValue   = minValue;
        copy.maxValue   = maxValue;
        copy.isCycle    = isCycle;
        copy.isAlways   = isAlways;

        return copy;
    }
