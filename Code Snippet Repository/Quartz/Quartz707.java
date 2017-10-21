  public void testEndingAtAfterCountOf0() {
    try {
      Date startTime = DateBuilder.dateOf(0,  0, 0, 1, 1, 2011);
      newTrigger()
          .withIdentity("test")
          .withSchedule(
              dailyTimeIntervalSchedule()
              .withIntervalInMinutes(15)
              .startingDailyAt(TimeOfDay.hourAndMinuteOfDay(8, 0))
              .endingDailyAfterCount(0))
          .startAt(startTime)
          .build();
      fail("We should not accept endingDailyAfterCount(0)");
    } catch (IllegalArgumentException e) {
      // Expected.
    }
    
    try {
      Date startTime = DateBuilder.dateOf(0,  0, 0, 1, 1, 2011);
      newTrigger()
          .withIdentity("test")
          .withSchedule(
              dailyTimeIntervalSchedule()
              .withIntervalInMinutes(15)
              .endingDailyAfterCount(1))
          .startAt(startTime)
          .build();
      fail("We should not accept endingDailyAfterCount(x) without first setting startingDailyAt.");
    } catch (IllegalArgumentException e) {
      // Expected.
    }
  }
