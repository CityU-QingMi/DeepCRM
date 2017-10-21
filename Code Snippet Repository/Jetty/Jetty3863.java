    public static int dispatch(DispatcherType type)
    {
        switch(type)
        {
          case REQUEST:
                  return REQUEST;
          case ASYNC:
                  return ASYNC;
          case FORWARD:
                  return FORWARD;
          case INCLUDE:
                  return INCLUDE;
          case ERROR:
                  return ERROR;
        }
        throw new IllegalArgumentException(type.toString());
    }
