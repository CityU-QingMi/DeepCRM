    public void removeSpecificRoutine(Routine routine) {

        for (int i = 0; i < this.routines.length; i++) {
            if (routines[i] == routine) {
                routines = (Routine[]) ArrayUtil.toAdjustedArray(routines,
                        null, i, -1);

                break;
            }
        }
    }
