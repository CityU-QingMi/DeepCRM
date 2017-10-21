    public int compare(StatCount sc1, StatCount sc2) {
        int compVal = sc1.getCount() < sc2.getCount() ? -1 :
                (sc1.getCount() == sc2.getCount() ? 0 : 1);
        
        if (compVal == 0) {
            compVal = sc1.getSubjectId().compareTo(sc2.getSubjectId());
            if (compVal == 0) {
                compVal = sc1.getTypeKey().compareTo(sc2.getTypeKey());   
            }
        }
        return compVal;
    }
