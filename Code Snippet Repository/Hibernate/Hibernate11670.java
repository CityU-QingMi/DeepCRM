      @Override
      protected Object handleDefault(InvocationContext ctx, VisitableCommand command) throws Throwable {
         // Failure in CommitCommand/RollbackCommand keeps some locks closed, therefore blocking the test
         if (!(command instanceof CommitCommand || command instanceof RollbackCommand)) {
            /* Introduce 5 % probability of failure */
            if (ThreadLocalRandom.current().nextInt(100) < 5) {
               throw new InducedException("Simulating failure somewhere");
            }
         }
         return super.handleDefault(ctx, command);
      }
