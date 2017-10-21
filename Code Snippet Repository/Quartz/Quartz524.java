    public int computeNumTimesFiredBetween(Date start, Date end) {

        if(repeatInterval < 1) {
            return 0;
        }
        
        long time = end.getTime() - start.getTime();

        return (int) (time / repeatInterval);
    }
