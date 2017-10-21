    private void ignoreFail()
    {
        State current = _state.get();
        while (current.getType()==StateType.FAILED)
        {
            if (updateState(current,__IDLE))
                return;
            current = _state.get();
        }
    }
