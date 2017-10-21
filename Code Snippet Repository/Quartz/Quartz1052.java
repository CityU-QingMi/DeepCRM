    void unlock() {
      if (--holdCount <= 0) {
        try {
          txnHandle.commit();
        } catch (RejoinException e) {
          throw new RejoinException("Exception caught during commit, transaction may or may not have committed.", e);
        } finally {
          threadState.remove();
        }
      }
    }
