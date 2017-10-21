    protected Object readResolve() throws ObjectStreamException {
        //
        //  if the deserialized object is exactly an instance of Level
        //
        if (getClass() == Level.class) {
            return toLevel(level);
        }
        //
        //   extension of Level can't substitute stock item
        //
        return this;
    }
