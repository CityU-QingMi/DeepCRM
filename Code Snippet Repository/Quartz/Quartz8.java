  public static void main(String[] args) throws Exception {
    boolean clearJobs = false;
    boolean scheduleJobs = true;

    for (String arg : args) {
      if (arg.equalsIgnoreCase("clearJobs")) {
        clearJobs = true;
      } else if (arg.equalsIgnoreCase("dontScheduleJobs")) {
        scheduleJobs = false;
      }
    }

    ClusterExample example = new ClusterExample();
    example.run(clearJobs, scheduleJobs);
  }
