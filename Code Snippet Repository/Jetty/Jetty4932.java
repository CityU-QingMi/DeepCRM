    public void ifExceptionThrowRuntime()
        throws Error
    {
        if(nested == null)
            return;
        
        switch (nested.size())
        {
          case 0:
              break;
          case 1:
              Throwable th=nested.get(0);
              if (th instanceof Error)
                  throw (Error)th;
              else if (th instanceof RuntimeException)
                  throw (RuntimeException)th;
              else
                  throw new RuntimeException(th);
          default:
              throw new RuntimeException(this);
        }
    }
