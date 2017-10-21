    public boolean tryProduce(boolean reproduce)
    {
        boolean producing = false;
        try (Lock locked = _locker.lock())
        {
            switch (_state)
            {
                case IDLE:
                    // Enter PRODUCING
                    _state = State.PRODUCING;
                    producing = true;
                    break;
                    
                case PRODUCING:
                    // Keep other Thread producing
                    if (reproduce)
                        _state = State.REPRODUCING;
                    break;
                    
                default:
                    break;
            }
        }
        return producing;
    }
