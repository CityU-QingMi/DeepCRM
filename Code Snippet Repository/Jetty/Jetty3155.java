    public void setWorkerName(String workerName)
    {
        if (isRunning())
            throw new IllegalStateException(getState());
        if (workerName == null)
            _workerName = "";
        else
        {
            if (workerName.contains("."))
                throw new IllegalArgumentException("Name cannot contain '.'");
            _workerName=workerName;
        }
    }
