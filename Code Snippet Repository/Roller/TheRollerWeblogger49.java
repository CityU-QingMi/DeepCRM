    public static boolean isValidDateRange(Date startDate, Date endDate, boolean equalOK) {
        // false if either value is null
        if (startDate == null || endDate == null) { return false; }
        
        if (equalOK && startDate.equals(endDate)) {
            // true if they are equal
            return true;
        }
        
        // true if endDate after startDate
        if (endDate.after(startDate)) {
            return true;
        }
        
        return false;
    }
