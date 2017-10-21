    public final synchronized void remove(int position) {

        hasChanged = true;

        moveRows(position + 1, position, count - position - 1);

        count--;

        keys[count]   = 0;
        values[count] = 0;
    }
