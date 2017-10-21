    public DateBuilder atHourMinuteAndSecond(int atHour, int atMinute, int atSecond) {
        validateHour(atHour);
        validateMinute(atMinute);
        validateSecond(atSecond);
        
        this.hour = atHour;
        this.second = atSecond;
        this.minute = atMinute;
        return this;
    }
