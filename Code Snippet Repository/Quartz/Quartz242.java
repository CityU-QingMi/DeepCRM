    public int compare(java.util.Calendar c1, java.util.Calendar c2) {
        
        int month1 = c1.get(java.util.Calendar.MONTH);
        int month2 = c2.get(java.util.Calendar.MONTH);
        
        int day1 = c1.get(java.util.Calendar.DAY_OF_MONTH);
        int day2 = c2.get(java.util.Calendar.DAY_OF_MONTH);
        
        if (month1 < month2) {
            return -1;
        }
        if (month1 > month2) {
            return 1; 
        }
        if (day1 < day2) {
            return -1;
        }
        if (day1 > day2) {
            return 1;
        }
        return 0;
      }
