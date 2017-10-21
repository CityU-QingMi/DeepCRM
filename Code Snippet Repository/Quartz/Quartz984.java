    public boolean canIgnore(ThreadInfo info) {
      if (info.getThreadName().startsWith(threadNamePrefix)) {

        if (firstFramePackage == null) {
          return true;
        } else {
          StackTraceElement[] stack = info.getStackTrace();
          if (stack.length > 1) {
            StackTraceElement frame = stack[stack.length - 2];
            if (frame.getClassName().startsWith(firstFramePackage)) {
              return true;
            }
          } else {
            throw new AssertionError("Failed checking ignore for: " + info);
          }
        }
      }

      return false;
    }
