    protected void updateResponse(Request request)
    {
        Response response = request.getResponse();
        if (request.isHandled())
        {
            switch (response.getStatus() / 100)
            {
                case 1:
                    _responses1xx.increment();
                    break;
                case 2:
                    _responses2xx.increment();
                    break;
                case 3:
                    _responses3xx.increment();
                    break;
                case 4:
                    _responses4xx.increment();
                    break;
                case 5:
                    _responses5xx.increment();
                    break;
                default:
                    break;
            }
        }
        else
            // will fall through to not found handler
            _responses4xx.increment();
        _responsesTotalBytes.add(response.getContentCount());
    }
