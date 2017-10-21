  @Override
  public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
    if (delegate.tryLock(time, unit)) {
      try {
        threadState.get().lock();
      } catch (RejoinException e) {
        delegate.unlock();
      }
      return true;
    } else {
      return false;
    }
  }
