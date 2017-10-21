   @Override
   protected Object handleDefault(InvocationContext ctx, VisitableCommand command) throws Throwable {
      boolean succeeded = false;
      try {
         log.tracef("Before command %s", command);
         Object retval = super.handleDefault(ctx, command);
         succeeded = true;
         return retval;
      } finally {
         log.tracef("After command(successful=%s) %s", succeeded, command);
         List<Runnable> toExecute = new ArrayList<>();
         synchronized (this) {
            for (Iterator<Condition> iterator = conditions.iterator(); iterator.hasNext(); ) {
               Condition condition = iterator.next();
               log.tracef("Testing condition %s", condition);
               if (condition.success != null && condition.success != succeeded) {
                  log.trace("Condition test failed, succeeded: " + succeeded);
               } else if (condition.predicate.test(ctx, command)) {
                  assert condition.action != null;
                  log.trace("Condition succeeded");
                  toExecute.add(condition.action);
                  if (condition.removeCheck == null || condition.removeCheck.getAsBoolean()) {
                     iterator.remove();
                  }
               } else {
                  log.trace("Condition test failed");
               }
            }
         }
         // execute without holding the lock
         for (Runnable runnable : toExecute) {
            log.tracef("Executing %s", runnable);
            runnable.run();
         }
      }
   }
