  public static void main(String[] args) throws Exception {

    int numberOfJobs = 500;
    if (args.length == 1) {
      numberOfJobs = Integer.parseInt(args[0]);
    }
    if (args.length > 1) {
      System.out.println("Usage: java " + LoadExample.class.getName() + "[# of jobs]");
      return;
    }
    LoadExample example = new LoadExample(numberOfJobs);
    example.run();
  }
