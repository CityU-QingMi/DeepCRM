        public void run() {

            try {
                do {
                    final Task task = HsqlTimer.this.nextTask();

                    if (task == null) {
                        break;
                    }

                    // PROBLEM: If the runnable throws an exception other
                    //          than InterruptedException (which likely stems
                    //          naturally from calling shutdownImmediately()
                    //          or getThread().interrupt()), this will still
                    //          cause the loop to exit, which is to say that
                    //          task scheduling will stop until a new task is
                    //          added or the timer is restarted directly, even
                    //          though there may still be uncancelled tasks
                    //          left on the queue.
                    //
                    // TODO:    Clarify and establish a contract regarding
                    //          the difference between InterruptedException,
                    //          RuntimeException and other things, like
                    //          UndeclaredThrowableException.
                    //
                    // SOL'N:   At present, we simply require each runnable to
                    //          understand its part of the implicit contract,
                    //          which is to deal with exceptions internally
                    //          (not throw them up to the timer), with the
                    //          possible exception of InterruptedException.
                    //
                    //          If the integrity of work performed by the
                    //          runnable may be adversely affected by an
                    //          unplanned interruption, the runnable should
                    //          deal with this directly, for instance by
                    //          catching the InterruptedException, ensuring
                    //          that some integrity preserving state is
                    //          attained, and then rethrowing the exception.
                    task.runnable.run();
                } while (true);
            } finally {
                HsqlTimer.this.clearThread();
            }
        }
