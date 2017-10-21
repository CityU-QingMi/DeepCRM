    protected void resetAccessCount() {

        if (accessCount < ACCESS_MAX) {
            return;
        }

        int    i      = accessTable.length;
        double factor = (double) accessMin / accessCount;

        if (factor < 0.5) {
            factor = 0.5;
        }

        while (--i >= 0) {
            if (accessTable[i] < accessMin) {
                accessTable[i] = 0;
            } else {
                accessTable[i] = (int) ((accessTable[i] - accessMin) * factor);
            }
        }

        accessCount = (int) ((accessCount - accessMin) * factor);
        accessMin   = 0;
    }
