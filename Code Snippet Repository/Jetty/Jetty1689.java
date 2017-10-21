    @Override
    public void onFillable()
    {
        while (true)
        {
            int filled = fill();
            if (completed || filled < 0)
            {
                replaceConnection();
                break;
            }
            if (filled == 0)
            {
                fillInterested();
                break;
            }
        }
    }
