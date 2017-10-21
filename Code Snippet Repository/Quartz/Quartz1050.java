  @Override
  public boolean tryLock() {
    if (delegate.tryLock()) {
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
