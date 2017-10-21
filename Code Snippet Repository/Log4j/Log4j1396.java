    @Override
    public void format(final StringBuilder toAppendTo, final Object... objects) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] instanceof Integer) {
                format(objects[i], toAppendTo);
                break;
            } else if (objects[i] instanceof NotANumber) {
                toAppendTo.append(NotANumber.VALUE);
                break;
            }
        }
    }
