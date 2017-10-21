    public Object getSequenceValue(NumberSequence sequence) {

        if (sequenceMap == null) {
            sequenceMap       = new HashMap();
            sequenceUpdateMap = new HashMap();
        }

        HsqlName key   = sequence.getName();
        Object   value = sequenceMap.get(key);

        if (value == null) {
            value = sequence.getValueObject();

            sequenceMap.put(key, value);
            sequenceUpdateMap.put(sequence, value);
        }

        return value;
    }
