    private void reset()
    {
        state = State.PREPARE;
        cursor = 0;
        length = 0;
        paddingLength = 0;
        exclusive = false;
        parentStreamId = 0;
        weight = 0;
    }
