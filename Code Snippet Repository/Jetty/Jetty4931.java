    public void ifExceptionThrow()
        throws Exception
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
              if (th instanceof Exception)
                  throw (Exception)th;
          default:
              throw this;
        }
    }
